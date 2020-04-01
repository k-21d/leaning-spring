package com.k21d.learning.spring.bean.definition;

import com.k21d.learning.spring.bean.factory.DefaultFactory;
import com.k21d.learning.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);

        applicationContext.refresh();
        System.out.println("Spring 上下文已经启动");
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring 上下文正在关闭");
        applicationContext.close();
        System.out.println("Spring 上下文关闭");
    }

    @Bean(initMethod = "initUserFactory",destroyMethod = "doDestory")
    @Lazy
    UserFactory userFactory(){
        return new DefaultFactory();
    }
}
