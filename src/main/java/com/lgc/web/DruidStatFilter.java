package com.lgc.web;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class DruidStatFilter
 */
@WebFilter(filterName="druidFilter", urlPatterns="/*", initParams={
		@WebInitParam(name="exclusions", value="*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {


}
