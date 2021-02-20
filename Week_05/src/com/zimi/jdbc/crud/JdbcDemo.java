package com.zimi.jdbc.crud;

import com.zimi.jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.zimi.jdbc.JdbcUtils.*;

public class JdbcDemo {

    @Test
    public void insert(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = getConnection();
            st = conn.createStatement();
            String sql = "insert into t_student(id,name) values(1001,'张三')";
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("插入成功！！");
            }else{
                System.out.println("插入失败！！");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            release(conn, st, rs);
        }
    }

    @Test
    public void update(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
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
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void delete(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "delete from t_student where id=1001";
            st = conn.createStatement();
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("删除成功！！");
            }else{
                System.out.println("删除失败！！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void findById(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from t_student where id=1001";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                System.out.println(rs.getString("name"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
}
