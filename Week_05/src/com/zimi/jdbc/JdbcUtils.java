package com.zimi.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 */
public class JdbcUtils {

    private static String DB_DRIVER = null;
    private static String DB_URL = null;
    private static String DB_USER_NAME = null;
    private static String DB_PASSWORD = null;

    static{
        try{
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties prop = new Properties();
            prop.load(in);

            DB_DRIVER = prop.getProperty("driver");
            DB_URL = prop.getProperty("url");
            DB_USER_NAME = prop.getProperty("username");
            DB_PASSWORD = prop.getProperty("password");

            Class.forName(DB_DRIVER);

        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }


    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME,DB_PASSWORD);
        System.out.println("connection:"+connection);
        return connection;
    }

    public static void release(Connection conn, Statement st, ResultSet rs){

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
