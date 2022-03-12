package com.win.dao.impl;

import java.sql.SQLException;
import java.util.List;
import com.win.util.DBHelper;
import com.win.dao.ICileDao;
import com.win.pojo.CileInfo;
import com.win.pojo.MageInfo;

public class CileDao implements ICileDao {
    //根据id查询一条客户数据
	@Override
	public CileInfo queryOne(String id) {
		String sql="select * from cile_info where id= "+id;;
		CileInfo cileInfo=null;
		try {
			cileInfo = DBHelper.executeQueryToOne(sql, CileInfo.class, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cileInfo;
	}
    //查询所有的客户数据以及所分配的员工姓名
	@Override
	public List<CileInfo> query() {
	    String sql="select a.id,a.sex,a.cileName,b.name as staffName \r\n"
           +"from cile_info as a \r\n"+
	       "left join staff_info as b on a.stfId=b.id";
	    List<CileInfo> list=null;
	    try {
            list=DBHelper.executeQuery(sql, CileInfo.class, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//查询客户的消费——客户姓名，客户消费
	@Override
	public List<CileInfo> query2() {
		String sql="select a.id,a.cileName,a.value from cile_info as a ";
			    List<CileInfo> list=null;
			    try {
		            list=DBHelper.executeQuery(sql, CileInfo.class, null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return list;
	}
	//根据id和姓名 查询客户的消费——客户姓名，客户消费
		@Override
		public List<CileInfo> query4(CileInfo cf) {
			StringBuffer sql=new StringBuffer("select a.id,a.cileName,a.value from cile_info as a where 1=1");
			if(cf.getId()!=null) sql.append(" and a.id='" +cf.getId()+ "'");
			if(cf.getCileName()!=null) sql.append(" and a.cileName='" +cf.getCileName()+ "'");
			List<CileInfo> list=null;
			try {
				list=DBHelper.executeQuery(sql.toString(), CileInfo.class, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
	//查询出客户的全部信息，主键与外键关联查询出所分配的员工姓名
	@Override
	public List<CileInfo> query3(CileInfo cf){
		StringBuffer sql= new StringBuffer("select a.id,a.sex,a.cileName,b.name as staffName "
				+ "from cile_info as a left join staff_info as b "
				+ "on a.stfId=b.id "
				+ "where 1=1");
		 if(cf.getId()!=null) sql.append(" and a.id='" +cf.getId()+ "'");
		 if(cf.getCileName()!=null) sql.append(" and a.cileName='" +cf.getCileName()+ "'");
		 List<CileInfo> list=null;
		 try
		 {
			list=DBHelper.executeQuery(sql.toString(), CileInfo.class, null);
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		 
	}
	@Override
	public Integer del(String id) {
		String sql = "delete from cile_info where id = " +id;
		Integer in=null;
		try {
			in=DBHelper.excuteUpdate(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
	//更新客户里的员工id属性
	@Override
	public Integer update(CileInfo dt) {
		String sql ="update cile_info set stfId = ? where id = ?";
		Integer res = null;
        try 
        {
			res=DBHelper.excuteUpdate(sql, dt.getStfId(), dt.getId());
		} 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	//更新客户的消费信息
		@Override
		public Integer update2(CileInfo dt) {
			String sql ="update cile_info set value = ? where id = ?";
			Integer res = null;
	        try 
	        {
				res=DBHelper.excuteUpdate(sql, dt.getStfId(), dt.getId());
			} 
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
		@Override
		public Integer add(CileInfo dt) {
			String sql = "insert into cile_info(id,cileName,stfId,value,sex)values(?,?,?,?,?)";
			Integer newDeptId=null;
			try 
			{
				newDeptId = DBHelper.excuteInsert(sql, dt.getId(), dt.getCileName(),dt.getStfId(),dt.getValue(),dt.getSex());
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newDeptId;
		}
}
