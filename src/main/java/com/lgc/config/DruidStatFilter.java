package com.lgc.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @program: javaConfig-ssm
 * @description: druid filter
 * @author: lgc
 * @create: 2019-06-01 17:25
 **/
@WebFilter(filterName="druidFilter", urlPatterns="/*", initParams={
        @WebInitParam(name="exclusions", value="*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")
})

public class DruidStatFilter extends WebStatFilter {
}
