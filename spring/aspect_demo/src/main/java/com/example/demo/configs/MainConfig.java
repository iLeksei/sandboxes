package com.example.demo.configs;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Bean
    public CustomPointcut customPointcut() {
        return new CustomPointcut();
    }

    @Bean
    public ExceptionsHandlerAspect exceptionsHandlerAspect() {
        return new ExceptionsHandlerAspect();
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        return new DefaultPointcutAdvisor(customPointcut(), exceptionsHandlerAspect());
    }


}
