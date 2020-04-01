package com.k21d.learning.spring.bean.definition;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-creation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(user);
        User instance = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(user==instance);
        //FactoryBean方式
        User factoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user==factoryBean);
    }
}
