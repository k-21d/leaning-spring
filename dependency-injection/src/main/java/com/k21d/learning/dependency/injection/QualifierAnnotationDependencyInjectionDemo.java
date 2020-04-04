package com.k21d.learning.dependency.injection;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;
    @Bean
    @Qualifier
    public User user1(){
        User user = new User();
        user.setId(8L);
        return user;
    }
    @Bean
    @Qualifier
    public User user2(){
        User user = new User();
        user.setId(9L);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);


        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);


        applicationContext.refresh();
        //依赖查找
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);
        System.out.println(demo.namedUser);
        System.out.println(demo.allUsers);
        System.out.println(demo.qualifierUsers);
        System.out.println(demo.user1());
        System.out.println(demo.user2());

        applicationContext.close();
    }
}
