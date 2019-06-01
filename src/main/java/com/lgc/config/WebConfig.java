package com.lgc.config;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: javaConfig-ssm
 * @description: initializer DispatcherServlet
 * @author: lgc
 * @create: 2019-06-01 15:01
 **/
@Configuration
@EnableWebMvc
@ComponentScan( "com.lgc.controller" )
public class WebConfig  extends WebMvcConfigurerAdapter {

    /*@Bean
     * 原springMVC的jsp视图解析器
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }*/

    @Bean(initMethod = "init")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        return beetlGroupUtilConfiguration;
    }
    @Bean(name = "viewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver() {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        return beetlSpringViewResolver;
    }

    //多文件上传，bean必须写name属性且必须为multipartResolver，不然取不到文件对象
    @Bean(name="multipartResolver")
    protected CommonsMultipartResolver MultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));//可以不设置
        multipartResolver.setMaxUploadSize(2097152);//2M
        multipartResolver.setMaxInMemorySize(0);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

    //静态资源的处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
