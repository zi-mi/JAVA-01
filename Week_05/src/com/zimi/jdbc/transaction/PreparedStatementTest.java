package com.zimi.jdbc.transaction;

import com.zimi.jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparedStatementTest {

    @Test
    public void testBatchByPreparedStatement(){

        long startTime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            String sql = "insert into t_student(id,name) values(?,?)";
            st = conn.prepareStatement(sql);
//          处理一万条sql，程序花费时间：814毫秒！！ 加了rewriteBatchedStatements=true
//          处理一万条sql，程序花费时间：41341毫秒！！没加rewriteBatchedStatements=true
            for(int i=30008;i<40008;i++){  //i=1000  2000
                st.setInt(1, i);
                st.setString(2, "student" + i);
                st.addBatch();

                if(i%1000==0){
                    st.executeBatch();
                    st.clearBatch();
                }
            }
            st.executeBatch();

        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("程序花费时间：" + (endTime-startTime) + "毫秒！！");
    }
}
