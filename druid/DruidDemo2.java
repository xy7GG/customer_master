package cn.database.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*完成添加操作，给account表添加一条记录*/
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
        	String sql="select count(*) from user2 where username=? and password=?";
			//用于将参数化SQL语句发送到数据库
            pstmt=conn.prepareStatement(sql);		
            //4.给？赋值
            pstmt.setString(1,"sa");
            pstmt.setDouble(2,3000);
            //5.执行sql
            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6. 释放资源
            JDBCUtils.close(pstmt,conn);
        }  
    }
}
