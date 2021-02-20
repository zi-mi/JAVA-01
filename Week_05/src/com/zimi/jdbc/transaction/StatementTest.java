package com.zimi.jdbc.transaction;

import com.zimi.jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementTest {

    @Test
    public void testbatch(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            String sql1 = "insert into t_student(id,name) values(1,'李四')";
            String sql2 = "insert into t_student(id,name) values(2,'王小二')";
            String sql3 = "insert into t_student(id,name) values(3,'燕小六')";

            st = conn.createStatement();
            st.addBatch(sql1);
            st.addBatch(sql2);
            st.addBatch(sql3);

            st.executeBatch();
            st.clearBatch();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
}
