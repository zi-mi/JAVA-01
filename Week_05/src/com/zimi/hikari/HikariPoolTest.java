package com.zimi.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.*;

public class HikariPoolTest {

    private static final HikariDataSource ds;

    static {
        HikariConfig conf = new HikariConfig();
        conf.setUsername("root");
        conf.setPassword("root");
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true");
        conf.setMinimumIdle(10);
        conf.setMaximumPoolSize(50);
        ds = new HikariDataSource(conf);
    }

    @Test
    public void testHikariPool() throws SQLException {
        long stime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        conn = ds.getConnection();
        String sql = "insert into t_student(id,name) values(?,?)";
        st = conn.prepareStatement(sql);
        for(int i=90008;i<100008;i++){
            st.setInt(1, i);
            st.setString(2, "student" + i);
            st.addBatch();

            if(i%1000==0){
                st.executeBatch();
                st.clearBatch();
            }
        }
        st.executeBatch();
        conn.close();
        //40128ms
        //添加rewriteBatchedStatements=true,334ms
        System.out.println(System.currentTimeMillis() - stime);
    }


}
