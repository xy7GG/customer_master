package cn.database.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*�����Ӳ�������account�����һ����¼*/
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1.��ȡ����
            conn = JDBCUtils.getConnection();
            //2.����sql
        	String sql="select count(*) from user2 where username=? and password=?";
			//���ڽ�������SQL��䷢�͵����ݿ�
            pstmt=conn.prepareStatement(sql);		
            //4.������ֵ
            pstmt.setString(1,"sa");
            pstmt.setDouble(2,3000);
            //5.ִ��sql
            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6. �ͷ���Դ
            JDBCUtils.close(pstmt,conn);
        }  
    }
}
