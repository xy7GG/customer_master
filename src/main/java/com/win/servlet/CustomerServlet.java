package com.win.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.win.dao.impl.CileDao;
import com.win.pojo.CileInfo;
import com.win.pojo.MageInfo;
public class CustomerServlet extends BaseServlet {
     private CileDao cileDao=new CileDao();
     
     public void toCilePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	 List<CileInfo> list=cileDao.query();
    	 request.setAttribute("list",list);
    	 request.getRequestDispatcher("/page/cile/cile_list.jsp").forward(request,response);
     }
     
     public void toValuePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	 List<CileInfo> list=cileDao.query2();
    	 request.setAttribute("value",list);
    	 request.getRequestDispatcher("/page/cile/cile_value.jsp").forward(request,response);
     }
     
	 public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//删除该客户
		String id = req.getParameter("id");
		int result = cileDao.del(id);
		if (result > 0) {
			responseObject("1", resp);
		} else {
			responseObject("0", resp);
		}
	}
	//查询出的客户列表
    public void CileList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
			//查询客户列表
			CileInfo cileInfo =new CileInfo();
			cileInfo.setId(req.getParameter("cileId"));
			cileInfo.setCileName(req.getParameter("cileName"));
			List<CileInfo> list=cileDao.query3(cileInfo);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/page/cile/cile_list.jsp").forward(req, resp);
		}
  //查询出的客户消费信息列表
    public void CileList2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
			//查询客户列表
			CileInfo cileInfo =new CileInfo();
			cileInfo.setId(req.getParameter("cileId"));
			cileInfo.setCileName(req.getParameter("cileName"));
			List<CileInfo> list=cileDao.query4(cileInfo);
			req.setAttribute("value", list);
			req.getRequestDispatcher("/page/cile/cile_value.jsp").forward(req, resp);
		}
	 //跳转到更新用户信息页面
	 public void toUpPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取客户id
		String id = req.getParameter("id");
		//根据id查询员工信息
		CileInfo cf = new CileInfo();
		cf.setId(id);
		CileInfo cileOne =cileDao.queryOne(id);
		req.setAttribute("cileOne", cileOne);
		//请求转发，跳转到更新员工信息页面
		req.getRequestDispatcher("/page/cile/cile_update.jsp").forward(req, resp);
	}
	
	//更新客户信息
	public void upCileInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("cileOne");
		//接收请求参数
		CileInfo cf = new CileInfo();		
		cf.setCileInfo(req);
		//----------------------------测试--------------------------------------
		//更新员工信息
		int result = cileDao.update(cf);		
		if (result < 1) {//更新失败
			responseObject("0", resp);
		} else {
			responseObject("1", resp);
		}
	} 
	 //跳转到更新用户消费信息页面
	 public void toUpPage2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取客户id
		String id = req.getParameter("id");
		//测试======================================================
		System.out.println(id);
		//根据id查询员工信息
		CileInfo cf = new CileInfo();
		cf.setId(id);
		CileInfo cileOne =cileDao.queryOne(id);
		req.setAttribute("cileTwo", cileOne);
		//请求转发，跳转到更新员工信息页面
		req.getRequestDispatcher("/page/cile/cile_value_update.jsp").forward(req, resp);
	}
	//更新客户花费金额
	public void upCileValueInfo(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("cileTwo");
		//接收请求参数
		CileInfo cf = new CileInfo();		
		cf.setCileInfo(req);

		//更新员工信息
		int result = cileDao.update2(cf);		
		if (result < 1) {//更新失败
			responseObject("0", resp);
		} else {
			responseObject("1", resp);
		}
	}
	//跳转到添加客户页面
	public void addCilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/page/cile/cile_add.jsp").forward(req, resp);
	}
	//添加客户信息
	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收请求参数
		CileInfo sf = new CileInfo();		
		sf.setCileInfo(req);
		String a=req.getParameter("value");
		System.out.println(a);
		System.out.println(sf.getValue());
		//添加员工信息
		Integer result = cileDao.add(sf);
		if (result < 1) {//插入失败
			responseObject("0", resp);
		} else {//插入成功
			responseObject("1", resp);
		}
	}
}
