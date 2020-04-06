package com.k21d.learning.bean.scope;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class BeanScopeDemo {
    @Bean
    public static User singletonUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Bean
    @Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser1")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser2")
    private User prototypeUser2;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        applicationContext.refresh();
        scopeBeanByLookup(applicationContext);
        scopeBeanByInjection(applicationContext);
        applicationContext.close();
    }

    private static void scopeBeanByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo bean = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println(bean.singletonUser);
        System.out.println(bean.singletonUser1);
        System.out.println(bean.prototypeUser);
        System.out.println(bean.prototypeUser1);
        System.out.println(bean.prototypeUser2);
    }

    private static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3 ; i++){
            //singleton是共享的Bean对象
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser="+singletonUser);
            //prototype每次是新创建的
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser="+prototypeUser);
        }
    }
}
