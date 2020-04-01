package com.k21d.learning.spring.bean.definition;

import com.k21d.learning.spring.bean.factory.DefaultFactory;
import com.k21d.learning.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-creation-context.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        ServiceLoader userFactoryServiceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(userFactoryServiceLoader);
        //demoServiceLoader();
        //通过AutowireCapableBeanFactory创建Bean
        UserFactory factoryBean = beanFactory.createBean(DefaultFactory.class);
        System.out.println(factoryBean.createUser());
    }

    public static void demoServiceLoader(){
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class,Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }
    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader){
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }

}
