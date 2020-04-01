package com.k21d.learning.spring.bean.definition;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User myUser = beanFactory.getBean("my-user",User.class);
        User user =  beanFactory.getBean("user",User.class);
        System.out.println(myUser==user);
    }
}
