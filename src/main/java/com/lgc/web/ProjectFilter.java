package com.lgc.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class WebFilter
 */
@WebFilter(filterName="projectFilter", urlPatterns={ "/*" })
public class ProjectFilter implements Filter{

	private final static Logger LOG = Logger.getLogger(ProjectFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		LOG.info("请求路径："+uri);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
