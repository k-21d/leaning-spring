package com.k21d.learning.spring.ioc.dependency.contatiner;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class AnnotationApplicationContextAsIoCContationerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIoCContationerDemo.class);
        applicationContext.refresh();
        lookupCollectionByType(applicationContext);
        applicationContext.close();
    }
    private static void lookupCollectionByType(BeanFactory beanFactory){
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象："+users);
        }
    }
    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("as");
        return user;
    }
}
