package com.win.dao;
import java.util.List;

import com.win.pojo.CileInfo;
import com.win.pojo.MageInfo;

public interface ICileDao {
   //查询，根据客户的id
   public CileInfo queryOne(String id);
   //查询客户列表
   public List<CileInfo> query();
   //查询客户消费列表
   public List<CileInfo> query2();
   //查询客户列表，根据id和name
   public List<CileInfo> query3(CileInfo cf);
   //根据id和姓名 查询客户的消费——客户姓名，客户消费
   public List<CileInfo> query4(CileInfo cf);
   //删除该顾客
   public Integer del(String id);
   //更改客户的所属员工
   public Integer update(CileInfo dt);
   //更新客户的消费信息
   public Integer update2(CileInfo dt);
   //添加客户信息
   public Integer add(CileInfo staffInfo);
} 
