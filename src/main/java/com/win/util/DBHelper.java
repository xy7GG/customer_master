
package com.win.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
/**
 * 数据库操作类
 */
public class DBHelper {
    private static ResultSet rs = null;//结果集
    private static DataSource ds;
    static{
        try {
            Properties pro = new Properties();
            pro.load(DBHelper.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
    	return ds.getConnection();
    } 
    /**
     * 释放资源
     */
    public static void close(Connection conn,Statement stmt){
       close(null,conn,stmt);	
    }
    public static void close(ResultSet rs ,Connection conn,Statement stmt){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取连接池方法
     */
    public static DataSource getDataSource(){
        return  ds;
    }
    /**
     * 执行数据库更新语句
     * @param sql  insert into user(name) values(?)
     * @param params   
     * @return true or false
     * @throws SQLException 
     */
    public static int excuteUpdate(String sql,Object... params) throws SQLException{
        int res = -2;//受影响的行数
        PreparedStatement pstmt = null;
        Connection conn=getConnection();
        try {
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);;//加载sql语句
            if(params != null){
                //替换占位符
                for(int i = 0; i < params.length; i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            //执行sql语句，返回执行成功的行数
            res = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
        	 close(conn, pstmt);
        }
        return res;
    }
    /**
     * 
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     * : Illegal operation on empty result set.对空结果集操作
     * 
     */
    //执行数据库插入语句
    public static int excuteInsert(String sql,Object... params) throws SQLException{
        int res=-2;//受影响的行数
        PreparedStatement pstmt = null;
        Connection conn=getConnection();
        try {
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);;//加载sql语句
            if(params!=null){
                //替换?占位符
                for(int i = 0; i < params.length; i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            res = pstmt.executeUpdate();//执行sql语句，返回受影响的行数
            rs = pstmt.getGeneratedKeys(); //获取新增记录的主键
            if(rs.next()){
            	System.out.println("结果集不为空!");
            }
            else{
            	System.out.println("结果集为空!"); 
            }
            return rs.getInt(1);//返回主键id
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
              close(conn,pstmt);
        }
        return res;
    }
    /**
     * 查询一条记录
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws SQLException 
     */
    public static <T> T executeQueryToOne(String sql,Class<T> cls,Object... params) throws SQLException{
    	List<T> list=executeQuery(sql, cls, params);
    	if (list.size() ==0 ) {
			return null;
		}
    	return list.get(0);
    }
                      
    //查询结果集（多条记录）
    public static <T> List<T> executeQuery(String sql,Class<T> cls,Object... params) throws SQLException
    {
    	PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn=getConnection();
    	List<T> data = new ArrayList<T>();
        try {
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);;//加载sql语句
            if(params!=null){
                //替换?占位符
                for(int i=0;i<params.length;i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            //执行sql查询语句，接收结果集
            rs = pstmt.executeQuery();
            //把查询出来的记录封装成对应的实体类对象
            
            //检索此ResultSet对象的列数、类型和属性
            ResultSetMetaData rsd = rs.getMetaData();  
            while(rs.next())
            { 
                T m = cls.newInstance(); 
                for(int i = 0; i < rsd.getColumnCount(); i++){
                	try {
	                    String col_name = rsd.getColumnLabel(i+1);//获得列名
	                    Object value = rs.getObject(col_name);//获得列所对应的值
	                    Field field = cls.getDeclaredField(col_name);
	                    field.setAccessible(true);//开启私有属性可访问
	                    field.set(m, value);//给对象的私有属性赋值
                	}catch (Exception e) {
						System.out.println("");
					}
                }
                data.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	 close(rs,conn,pstmt);
        }
        return data;
    }
}