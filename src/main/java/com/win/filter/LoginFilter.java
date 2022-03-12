package com.win.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * ���������servlet����֮ǰ���������������
 * ��֤�������ݵĺϷ����Լ���һЩserlvet����ִ��ǰ��ǰ�ô���
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
			//��������  
			fc.doFilter(arg0, arg1);
			return;
		}
		
		//��֤session�Ƿ񻺴��û���Ϣ
		HttpSession s = req.getSession();
		if(s.getAttribute("staff") == null) {
			//�����ض���
			resp.sendRedirect(req.getContextPath()+"/page/login.jsp");
		}else {
			fc.doFilter(arg0, arg1);//ͨ������
		}
	}

	@Override
	public void destroy() {

	}

}
