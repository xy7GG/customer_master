package com.win.servlet;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.win.dao.impl.MageDao;
import com.win.pojo.MageInfo;
import com.win.util.DBHelper;

public class ManagerServlet extends BaseServlet {
	private MageDao mageDao = new MageDao();
	
	//跳转到登陆页
	public void loginPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//请求转发，跳转到登陆页
		req.getRequestDispatcher("/page/login.jsp").forward(req, res);
	}
	
	//登录处理
	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//从请求中，根据参数名获取参数值
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		MageInfo mageInfo = mageDao.queryOne(user, pass);
		if (mageInfo == null) {
			//没查到，登陆失败
			responseObject("0", res);
		} else {
			//登录成功
			//添加用户信息到session缓存中去
			HttpSession session = req.getSession();
			//向session中添加用户信息
			session.setAttribute("staff",mageInfo);
			//设置session有效时常
			session.setMaxInactiveInterval(100);		
			responseObject("1", res);
		}		
	}
	
	//跳转到主页
	public void mainPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/page/main.jsp").forward(req, res);
	}
	
	//跳转到注册
	public void registerPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/page/register.jsp").forward(req, res);
	}
	
	//更新用户个人信息
	public void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询客户所属员工信息
		HttpSession session = req.getSession();
		MageInfo mageInfo=(MageInfo) session.getAttribute("staff");
		//更新用户信息
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String sex = req.getParameter("sex");
		mageInfo.setName(name);
		mageInfo.setSex(sex);
		mageInfo.setPass(pass);
		Integer result = mageDao.update(mageInfo);
		if (result > 0) {
			//想前端页面返回处理状态
			responseObject("1", resp);
		} else {
			responseObject("0", resp);
		}
	}
	
	//员工列表
	public void userList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//查询员工列表
		MageInfo mageInfo =new MageInfo();
		mageInfo.setId(req.getParameter("staffId"));
		mageInfo.setName(req.getParameter("staffName"));
		List<MageInfo> list=mageDao.query(mageInfo);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/page/user/user_list.jsp").forward(req, resp);
	}
	
	    //跳转到个人信息页面
		public void userInfoPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
		{
			HttpSession session = req.getSession();
			MageInfo staffInfo = (MageInfo) session.getAttribute("staff");
			req.getRequestDispatcher("/page/user/user_info.jsp").forward(req, res);
		}
	
	  //退出登录
	  public void logout(HttpServletRequest request, HttpServletResponse response)
	  {
		  request.getSession().invalidate();
		  try
		  {
			request.getRequestDispatcher("/page/login.jsp").forward(request, response);
		  }
		  catch (Exception e)
		  {
		    e.printStackTrace();
		  }
	  }
	  
		//跳转到更新员工信息页面
		public void upUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//获取员工id
			String id = req.getParameter("id");
			//根据id查询员工信息
			MageInfo sf = new MageInfo();
			sf.setId(id);
			List<MageInfo> list = mageDao.query(sf);
			MageInfo staffInfo = list.get(0);
			req.setAttribute("staff", staffInfo);
			//请求转发，跳转到更新员工信息页面
			req.getRequestDispatcher("/page/user/user_update.jsp").forward(req, resp);
		}
		
		//跳转到添加员工页面
		public void addUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/page/user/user_add.jsp").forward(req, resp);
		}
		//添加员工信息
		public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//接收请求参数
			MageInfo sf = new MageInfo();		
			sf.setMageInfo(req);
			//测试
			System.out.println(mageDao.queryUser(sf));
			//添加员工信息
			Integer result = mageDao.add(sf);
			if (result < 1) {//插入失败
				responseObject("0", resp);
			} else {//插入成功
				responseObject("1", resp);
			}
		}
		
		//删除员工
		public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//获取员工id
			String id = req.getParameter("id");
			//System.out.println();
			int result = mageDao.del(id);
			if (result > 0) 
			{
				responseObject("1", resp);
			} 
			else {
				responseObject("0", resp);
			}
		}
		//更新员工信息
		public void upUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//接收请求参数
			MageInfo sf = new MageInfo();		
			sf.setMageInfo(req);
			//更新员工信息
			int result = mageDao.update(sf);		
			if (result < 1) {//更新失败
				responseObject("0", resp);
			} else {
				responseObject("1", resp);
			}
		}	  
}

