package com.zimi.jdbc.transaction;

import com.zimi.jdbc.JdbcUtils;

import java.sql.*;

public class TransactionTest {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Savepoint sp = null;

        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);   //开启事务

            String sql1 = "update t_student set name='8998sssss' where id='8998'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

            sp = conn.setSavepoint();//设置事务回滚点

            String sql2 = "update t_student set name='ttttt9000' where id='9000'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            int x = 1/0;//异常行为

            String sql3 = "update t_student set name='9005eeeee' where id='9005'";
            st = conn.prepareStatement(sql3);
            st.executeUpdate();

            conn.commit();

        }catch (Exception e) {
            try {
                conn.rollback(sp);//回滚到事务回滚点
                conn.commit();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }

    }
}
