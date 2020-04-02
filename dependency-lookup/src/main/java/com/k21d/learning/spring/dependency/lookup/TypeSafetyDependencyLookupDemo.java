package com.k21d.learning.spring.dependency.lookup;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();
        //演示BeanFactory#getBean方法的安全性
        //displayBeanFactory(applicationContext);
        //演示ObjectFactory#getObject方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        applicationContext.close();
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        //等价于ObjectProvider
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException(()->userObjectFactory.getObject());
    }

    public static void displayBeanFactory(BeanFactory beanFactory){
        printBeansException(()->beanFactory.getBean(User.class));
    }
    private static void printBeansException(Runnable runnable){
        try {
            runnable.run();
        }catch (BeansException e){
            e.printStackTrace();
        }
    }
}
