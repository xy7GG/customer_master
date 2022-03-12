package com.win.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.win.dao.IMageDao;
import com.win.pojo.MageInfo;
import com.win.util.DBHelper;

public class MageDao implements IMageDao{
	
	//根据账号密码获取一条数据的链接
	public MageInfo queryOne(String user, String pass) {
		String sql = "select * from staff_info where user = ? and pass = ?";
		MageInfo mageInfo=null;
		try {
			mageInfo = DBHelper.executeQueryToOne(sql, MageInfo.class, 
					user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mageInfo;
	}
	
	//获取全部数据
	public List<MageInfo> query(MageInfo sf) {		
		StringBuffer sql = new StringBuffer("select * from staff_info where 1=1");
		if (sf.getId() != null) sql.append(" and id = '" + sf.getId() + "'");
		if (sf.getName() != null) sql.append(" and name = '" + sf.getName() + "'");
		List<MageInfo> list=null;
		try {
			list = DBHelper.executeQuery(sql.toString(), MageInfo.class, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	public Integer update(MageInfo mageInfo) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("update staff_info set ");
		if(mageInfo.getName()!= null) {
			sql.append("name = '" + mageInfo.getName() + "',");
		}
		if(mageInfo.getPass()!=null) {
			sql.append("pass = " + mageInfo.getPass() + ",");
		}
		if(mageInfo.getUser() != null) {
			sql.append("user = '" + mageInfo.getUser() + "',");
		}
		sql = new StringBuffer(sql.substring(0, sql.length()-1));
		sql.append(" where id = '" + mageInfo.getId()+"'");
		int res=0;
		try {
			res = DBHelper.excuteUpdate(sql.toString(),null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 新建和修改员工信息时检索user&id
	 * Bug：无法将在数据库中查询的数据返回到对象中
	 */
	public MageInfo queryUser(MageInfo sf)
	{
		String sqlUser=null;
		MageInfo mageInfo=null;
		System.out.println(sf.getId()+"   "+sf.getUser());
		if(sf.getUser()!=null) 
		{
	      sqlUser="select * from staff_info where id= ? or user= ?";
	    try 
	    {
		    mageInfo=DBHelper.executeQueryToOne(sqlUser,MageInfo.class,sf.getId(),sf.getUser());
		} 
	      catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return mageInfo;
	}
	//添加员工
	@Override
	public Integer add(MageInfo sf) {
		StringBuffer sql = new StringBuffer("insert into staff_info");
		StringBuffer columnSql = new StringBuffer();
		StringBuffer valueSql = new StringBuffer();
		if(sf.getId() != null) { 
			columnSql.append("id,");
			valueSql.append("'"+sf.getId()+"',");
		}
		if(sf.getName() != null) {
			columnSql.append("name,");
			valueSql.append("'"+sf.getName()+"',");
		}
		if(sf.getSex() != null) {
			columnSql.append("sex,");
			valueSql.append(sf.getSex()+",");
		}
		if(sf.getUser() != null) {
			columnSql.append("user,");
			valueSql.append("'"+sf.getUser()+"',");
		}
		if(sf.getUser() != null) {
			columnSql.append("pass,");
			valueSql.append("'"+sf.getPass()+"',");
		}
		if(columnSql.length() == 0) {
			return -1;
		} 
		else {
			sql.append("("+columnSql.substring(0, columnSql.length()-1)+")");
			sql.append("values("+valueSql.substring(0, valueSql.length()-1)+");");
			Integer res=null;
			try {
				res = DBHelper.excuteInsert(sql.toString(), null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
	}
	
	@Override
	public Integer del(String id) {
		String sql = "delete from staff_info where id = " +id;
		Integer res=null;
		try {
			res=DBHelper.excuteUpdate(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}