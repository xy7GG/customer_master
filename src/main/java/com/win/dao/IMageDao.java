package com.win.dao;

import java.util.List;

import com.win.pojo.CileInfo;
import com.win.pojo.MageInfo;


public interface IMageDao {
	//方法的签名：作用是用来定义方法
	//查询，根据用户名和密码
	public MageInfo queryOne(String user, String pass);
    //更新员工信息
	public Integer update(MageInfo staffInfo);
	//查询员工列表
	public List<MageInfo> query(MageInfo staffInfo);
	//添加员工信息
	public Integer add(MageInfo staffInfo);
	//删除员工信息
	public Integer del(String id);
    //判断添加的账号或id在数据库中是否已经存在
	public MageInfo queryUser(MageInfo sf);
}
