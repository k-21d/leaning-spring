package com.k21d.learning.spring.ioc.dependency.injection;

import com.k21d.learning.spring.ioc.domain.User;
import com.k21d.learning.spring.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        //System.out.println(userRepository.getUsers());
        //依赖注入
        System.out.println(userRepository.getBeanFactory()==beanFactory);
        //依赖查找
        //System.out.println(beanFactory.getBean(BeanFactory.class));
        ApplicationContext context = userRepository.getObjectFactory().getObject();
        System.out.println(context==beanFactory);
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }
}
