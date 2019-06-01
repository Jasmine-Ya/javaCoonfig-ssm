package com.lgc.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @program: javaConfig-ssm
 * @description: javaConfig initializer DispatcherServlet
 * @author: lgc
 * @create: 2019-06-01 14:56
 **/
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final static Logger LOG = Logger.getLogger(WebAppInitializer.class);
    
    /** 
    * @Description: root initializer 
    * @Param: [] 
    * @return: java.lang.Class<?>[] 
    * @Author: lgc 
    * @Date: 2019-06-01 
    */ 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOG.info("------root配置类初始化------");
        return new Class<?>[] { RootConfig.class };
    }

    /** 
    * @Description: webConfig initializer 
    * @Param: [] 
    * @return: java.lang.Class<?>[] 
    * @Author: lgc 
    * @Date: 2019-06-01 
    */ 
    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOG.info("------web配置类初始化------");
        return new Class<?>[] { WebConfig.class };
    }

    /** 
    * @Description: mapper initializer
    * @Param: [] 
    * @return: java.lang.String[] 
    * @Author: lgc 
    * @Date: 2019-06-01 
    */ 
    @Override
    protected String[] getServletMappings() {
        LOG.info("------映射根路径初始化------");
        return new String[]{ "/" };//请求路径映射，根路径
    }
}
