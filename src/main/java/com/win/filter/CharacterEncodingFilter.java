package com.win.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 字符编码过滤器
 */
public class CharacterEncodingFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)req;
        HttpServletResponse httpResponse = (HttpServletResponse)res;
        //设置请求编码
        httpRequest.setCharacterEncoding("UTF-8");
        //设置响应编码
        httpResponse.setCharacterEncoding("UTF-8");
        //调用doFIlter方法,如果还有别的过滤器会自动向下调用
        chain.doFilter(httpRequest, httpResponse);
	}

    @Override
    public void destroy() {

    }

}
