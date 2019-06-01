package com.lgc.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: javaConfig-ssm
 * @description: Persistence layer config
 * @author: lgc
 * @create: 2019-06-01 16:59
 **/
@Configuration
@ComponentScan(basePackages={ "com.lgc.config", "com.lgc.dao", "com.lgc.service" })
@Import(DruidDataSourceConfig.class)
public class RootConfig {
    @Bean
    public BeanNameAutoProxyCreator proxycreate() {
        BeanNameAutoProxyCreator proxycreate = new BeanNameAutoProxyCreator();
        proxycreate.setProxyTargetClass(true);
        proxycreate.setBeanNames("*ServiceImpl");
        proxycreate.setInterceptorNames("transactionInterceptor");
        return proxycreate;
    }
}
