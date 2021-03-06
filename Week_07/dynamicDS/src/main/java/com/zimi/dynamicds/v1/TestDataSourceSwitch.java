package com.zimi.dynamicds.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 简单模拟测试数据源读写切换
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
public class TestDataSourceSwitch {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Test
    public void test1() throws SQLException {
        System.out.println(jdbcTemplate.getDataSource() == dataSource); //true
        System.out.println(DataSourceUtils.getConnection(jdbcTemplate.getDataSource()));


        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            String sql = "update t_student set name='王五' where id=1001";
            st = conn.createStatement();
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("更新成功！！");
            }else{
                System.out.println("更新失败！！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            releaseConn(conn, st, rs);
        }



        DynamicDataSourceContextHolder.setDataSourceId(DynamicDataSourceId.SLAVE1);

        try{
            conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            String sql = "select * from t_student  where id=1001";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                System.out.println(rs.getString("name"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            releaseConn(conn, st, rs );
        }

        System.out.println(jdbcTemplate.getDataSource() == dataSource); //true
        System.out.println(DataSourceUtils.getConnection(jdbcTemplate.getDataSource()));


        // 完成操作后  最好把数据源再set回去  否则可能会对该线程后续再使用JdbcTemplate的时候造成影响
        DynamicDataSourceContextHolder.setDataSourceId(DynamicDataSourceId.MASTER);
    }

    private void releaseConn(Connection conn, Statement st, ResultSet rs ){
        if(rs!=null){
            try{
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;

        }
        if(st!=null){
            try{
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        if(conn!=null){
            try{
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
