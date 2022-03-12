package com.win.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.win.util.DBHelper;

public class BaseServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
        //req.getParameter()从请求中获取参数值
		String methodName = req.getParameter("method");
		try {
			//反射: 根据方法名methodName，获取到对应的servlet方法，并执行
			//这里的this指的是继承BaseServlet对象
			Method method = this.getClass().getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			//执行方法
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void responseObject(Object o,HttpServletResponse resp)throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		//创建输出流
		PrintWriter out=resp.getWriter();
		//写入数据
		out.write(o.toString());
		//刷新输出流，开始给前端页面返回数据
		out.flush();
		//关闭输出流
		out.close(); 
	}
	protected void responseStatus(int status,HttpServletResponse resp)throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.write("{\"status\":"+status+"}");
	}
}
