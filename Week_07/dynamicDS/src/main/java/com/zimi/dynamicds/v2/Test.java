package com.zimi.dynamicds.v2;

import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;

/**
 * 测试 ShardingSphere 读写分离
 *
 *
 * 观察通过 ShardingSphere 获取的 DataSource 是否会自动写入到主库，从库是否主动同步，从库同步数据的延迟时间
 */
public class Test {

    //主库 DataSource
    private static DataSource dsSlave = DataSourceUtil.createDataSource("127.0.0.1",
            "root", "root", "test_slave");
    //从库 DataSource
    private static DataSource dsMaster = DataSourceUtil.createDataSource("127.0.0.1",
            "root", "root", "test");

    public static void main(String[] args) throws SQLException {
        //启动线程打印主库与从库当前 t_student 数据量与时间，观察从库同步数据延迟
//        printMasterAndSlaveData();

        //从 ShardingSphere 获取 DataSource，观察插入数据的库是否为主库
        DataSource ds = getMasterSlaveDataSource();
        Connection conn = ds.getConnection();
        Statement stt = conn.createStatement();
        stt.execute("insert into t_student values(2, 'fj')");


    }

    /**
     * 测试失败日志，原因还在查。  可能是数据库配置不是用的多个实例造成的
     * 03:35:27.710 [main] INFO org.apache.shardingsphere.core.log.ConfigurationLogger - MasterSlaveRuleConfiguration:
     * masterDataSourceName: ds_master
     * name: ds_master
     * slaveDataSourceNames:
     * - ds_slave
     *
     * 03:35:27.711 [main] INFO org.apache.shardingsphere.core.log.ConfigurationLogger - Properties:
     * {}
     *
     * Exception in thread "main" java.lang.AbstractMethodError: com.mysql.jdbc.JDBC4Connection.getSchema()Ljava/lang/String;
     * 	at com.zaxxer.hikari.pool.HikariProxyConnection.getSchema(HikariProxyConnection.java)
     * 	at org.apache.shardingsphere.sql.parser.binder.metadata.util.JdbcUtil.getSchema(JdbcUtil.java:41)
     * 	at org.apache.shardingsphere.sql.parser.binder.metadata.schema.SchemaMetaDataLoader.loadAllTableNames(SchemaMetaDataLoader.java:92)
     * 	at org.apache.shardingsphere.sql.parser.binder.metadata.schema.SchemaMetaDataLoader.load(SchemaMetaDataLoader.java:68)
     * 	at org.apache.shardingsphere.shardingjdbc.jdbc.core.context.MasterSlaveRuntimeContext.loadSchemaMetaData(MasterSlaveRuntimeContext.java:55)
     * 	at org.apache.shardingsphere.shardingjdbc.jdbc.core.context.MultipleDataSourcesRuntimeContext.createMetaData(MultipleDataSourcesRuntimeContext.java:57)
     * 	at org.apache.shardingsphere.shardingjdbc.jdbc.core.context.MultipleDataSourcesRuntimeContext.<init>(MultipleDataSourcesRuntimeContext.java:51)
     * 	at org.apache.shardingsphere.shardingjdbc.jdbc.core.context.MasterSlaveRuntimeContext.<init>(MasterSlaveRuntimeContext.java:43)
     * 	at org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.MasterSlaveDataSource.<init>(MasterSlaveDataSource.java:47)
     * 	at org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory.createDataSource(MasterSlaveDataSourceFactory.java:47)
     * 	at com.zimi.dynamicds.v2.Test.getMasterSlaveDataSource(Test.java:85)
     * 	at com.zimi.dynamicds.v2.Test.main(Test.java:35)
     */

    /**
     * 启动线程打印，两个主从库 t_student 表的信息、数据量、当前时间
     * @throws SQLException
     */
    private static void printMasterAndSlaveData() throws SQLException {
        Connection masterConn = dsMaster.getConnection();
        Connection slaveConn = dsSlave.getConnection();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("------master------" + LocalTime.now());
                    print(masterConn);
                    System.out.println("------slave------" + LocalTime.now());
                    print(slaveConn);
                } catch (SQLException e) {
                }
            }
        }).start();
    }

    private static void print(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("select * from t_student");
        ResultSet rs = statement.getResultSet();
        int count = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id + "-" + name);
            count++;
        }
        System.out.println("total: " + count);
    }

    /**
     * 设置 ShardingSphere 的主从库
     * @return
     * @throws SQLException
     */
    private static DataSource getMasterSlaveDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master",
                "ds_master", Arrays.asList("ds_slave"));
        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig, new Properties());
    }

    /**
     * 用 主从库的 DataSource 构造 map
     * @return
     */
    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds_master", dsMaster);
        result.put("ds_slave", dsSlave);
        return result;
    }
}
