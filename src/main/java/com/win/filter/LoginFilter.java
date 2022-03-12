package com.win.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * 在请求进入servlet方法之前，对请求进行拦截
 * 验证请求数据的合法性以及做一些serlvet方法执行前的前置处理
 */
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		if(req.getParameter("method") != null && 
				req.getParameter("method").contains("login")) {
			//放行请求  
			fc.doFilter(arg0, arg1);
			return;
		}
		
		//验证session是否缓存用户信息
		HttpSession s = req.getSession();
		if(s.getAttribute("staff") == null) {
			//请求重定向
			resp.sendRedirect(req.getContextPath()+"/page/login.jsp");
		}else {
			fc.doFilter(arg0, arg1);//通过过滤
		}
	}

	@Override
	public void destroy() {

	}

}
