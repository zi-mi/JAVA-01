package com.cdu.utils;

import com.zimi.jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class OrderPreparedStatementTest {

    @Test
    public void testBatchByPreparedStatement(){

        long startTime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            String sql = "INSERT INTO `tb_order` (\n" +
                    "\t`order_id`,\n" +
                    "\t`payment`,\n" +
                    "\t`payment_type`,\n" +
                    "\t`post_fee`,\n" +
                    "\t`status`,\n" +
                    "\t`create_time`,\n" +
                    "\t`update_time`,\n" +
                    "\t`payment_time`,\n" +
                    "\t`consign_time`,\n" +
                    "\t`end_time`,\n" +
                    "\t`close_time`,\n" +
                    "\t`shipping_name`,\n" +
                    "\t`shipping_code`,\n" +
                    "\t`user_id`,\n" +
                    "\t`buyer_message`,\n" +
                    "\t`buyer_nick`,\n" +
                    "\t`buyer_rate`,\n" +
                    "\t`buyer_address`\n" +
                    ")\n" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            Date orderTime = new java.sql.Date(System.currentTimeMillis());
            for(int i=1900000;i<2900000;i++){  //i=1000  2000
                /*st.setString(1,OrderCodeFactory.getOrderCode(1l));*/
                st.setString(1,""+i);
                st.setInt(2,1000);
                st.setInt(3,1);
                st.setInt(4,1000);
                st.setInt(5,1);
                /*st.setDate(6, new java.sql.Date(System.currentTimeMillis()));
                st.setDate(7, new java.sql.Date(System.currentTimeMillis()));
                st.setDate(8, new java.sql.Date(System.currentTimeMillis()));
                st.setDate(9, new java.sql.Date(System.currentTimeMillis()));
                st.setDate(10, new java.sql.Date(System.currentTimeMillis()));
                st.setDate(11, new java.sql.Date(System.currentTimeMillis()));*/
                st.setDate(6, orderTime);
                st.setDate(7, orderTime);
                st.setDate(8, orderTime);
                st.setDate(9, orderTime);
                st.setDate(10, orderTime);
                st.setDate(11, orderTime);

                st.setString(12, "");
                st.setString(13, "");
                st.setInt(14, 1);
                st.setString(15, "maimiamai");
                st.setString(16, "m");
                st.setInt(17, 0);
                st.setInt(18, 12);
                st.addBatch();

                if(i%1000==0){
                    st.executeBatch();
                    conn.commit();
                    st.clearBatch();
                }
            }
            st.executeBatch();
            conn.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("程序花费时间：" + (endTime-startTime) + "毫秒！！");
        /**
         *
         *
         *
         *  订单表18个字段、rewriteBatchedStatements=true
         *
            *   connection:com.mysql.jdbc.JDBC4Connection@29444d75
         *   程序花费时间：108114毫秒！！
         *   Process finished with exit code 0
         *
         *
         *   connection:com.mysql.jdbc.JDBC4Connection@29444d75
         * 程序花费时间：118299毫秒！！
         *
         * Process finished with exit code 0
         *
         *
         *
         *C:\javaDevEnv\Java\jdk1.8.0_231\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:C:\javaDevEnv\ide\ideaIU-20200104\lib\idea_rt.jar=3925:C:\javaDevEnv\ide\ideaIU-20200104\bin -Dfile.encoding=UTF-8 -classpath C:\javaDevEnv\ide\ideaIU-20200104\lib\idea_rt.jar;C:\javaDevEnv\ide\ideaIU-20200104\plugins\junit\lib\junit5-rt.jar;C:\javaDevEnv\ide\ideaIU-20200104\plugins\junit\lib\junit-rt.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\charsets.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\deploy.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\access-bridge-64.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\cldrdata.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\dnsns.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\jaccess.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\jfxrt.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\localedata.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\nashorn.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunec.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunjce_provider.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunmscapi.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunpkcs11.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\zipfs.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\javaws.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jce.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jfr.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jfxswt.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jsse.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\management-agent.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\plugin.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\resources.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\rt.jar;C:\javaDevEnv\IdeaProjects\test\out\production\test;C:\Users\zimi\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\zimi\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\javaDevEnv\IdeaProjects\test\lib\mysql-connector-java-5.1.47.jar;C:\javaDevEnv\IdeaProjects\test\lib\HikariCP-3.4.5.jar;C:\javaDevEnv\IdeaProjects\test\lib\slf4j-api-1.7.25.jar com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 com.cdu.utils.OrderPreparedStatementTest,testBatchByPreparedStatement
         * connection:com.mysql.jdbc.JDBC4Connection@29444d75
         * 程序花费时间：142039毫秒！！
         *
         * Process finished with exit code 0
         *
         *
         * 不调用生成订单号方法时：
         * C:\javaDevEnv\Java\jdk1.8.0_231\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:C:\javaDevEnv\ide\ideaIU-20200104\lib\idea_rt.jar=3945:C:\javaDevEnv\ide\ideaIU-20200104\bin -Dfile.encoding=UTF-8 -classpath C:\javaDevEnv\ide\ideaIU-20200104\lib\idea_rt.jar;C:\javaDevEnv\ide\ideaIU-20200104\plugins\junit\lib\junit5-rt.jar;C:\javaDevEnv\ide\ideaIU-20200104\plugins\junit\lib\junit-rt.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\charsets.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\deploy.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\access-bridge-64.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\cldrdata.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\dnsns.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\jaccess.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\jfxrt.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\localedata.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\nashorn.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunec.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunjce_provider.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunmscapi.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\sunpkcs11.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\ext\zipfs.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\javaws.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jce.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jfr.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jfxswt.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\jsse.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\management-agent.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\plugin.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\resources.jar;C:\javaDevEnv\Java\jdk1.8.0_231\jre\lib\rt.jar;C:\javaDevEnv\IdeaProjects\test\out\production\test;C:\Users\zimi\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\zimi\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\javaDevEnv\IdeaProjects\test\lib\mysql-connector-java-5.1.47.jar;C:\javaDevEnv\IdeaProjects\test\lib\HikariCP-3.4.5.jar;C:\javaDevEnv\IdeaProjects\test\lib\slf4j-api-1.7.25.jar com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 com.cdu.utils.OrderPreparedStatementTest,testBatchByPreparedStatement
         * connection:com.mysql.jdbc.JDBC4Connection@29444d75
         * 程序花费时间：82429毫秒！！
         *
         * Process finished with exit code 0
         *
         * */
    }
}
