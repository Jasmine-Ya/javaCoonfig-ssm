package com.lgc.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

/**
 * @program: javaConfig-ssm
 * @description: aop Transaction
 * @author: lgc
 * @create: 2019-06-01 17:26
 **/

@Aspect
@Component
@ImportResource("classpath:/aop-config.xml")
public class AspectConfig {
    @Pointcut("execution(* com.lgc.service.*.*(..))")
    public void serviceAnnotatedClass() {
    }
}
