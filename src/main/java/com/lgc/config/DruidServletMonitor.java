package com.lgc.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @program: javaConfig-ssm
 * @description: druid Monitor
 * @author: lgc
 * @create: 2019-06-01 17:24
 **/
@WebServlet(name="druidMonitor", urlPatterns="/druid/*", initParams={
        @WebInitParam(name="allow", value="127.0.0.1"),
        @WebInitParam(name="loginUsername", value="admin"),
        @WebInitParam(name="loginPassword", value="123123"),
        @WebInitParam(name="resetEnable", value="false")
})

public class DruidServletMonitor extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}
