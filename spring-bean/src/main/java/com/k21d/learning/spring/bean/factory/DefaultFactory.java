package com.k21d.learning.spring.bean.factory;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultFactory implements UserFactory, InitializingBean, DisposableBean {
    //1.基于PostConstruct注解
    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct：UserFactory 初始化中");
    }

    public void initUserFactory(){
        System.out.println("自定义：UserFactory 初始化中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet()");
    }
    @PreDestroy
    public void preDestory(){
        System.out.println("@PreDestroy：销毁中");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean： destroy()");
    }

    public void doDestory(){
        System.out.println("自定义： doDestory");
    }
}
