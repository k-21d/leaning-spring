package com.k21d.learning.dependency.injection;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class AnnotationDependencyFieldnjectionDemo {
    //@Autowired //@Autowired会忽略静态字段
    private UserHolder userHolder;

    private UserHolder userHolder2;
    @Autowired
    public void initUserHolder(UserHolder userHolder){
        this.userHolder = userHolder;
    }
    @Resource
    public void init2(UserHolder userHolder){
        this.userHolder2 = userHolder;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldnjectionDemo.class);


        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);


        applicationContext.refresh();
        //依赖查找
        AnnotationDependencyFieldnjectionDemo bean = applicationContext.getBean(AnnotationDependencyFieldnjectionDemo.class);
        //@Autowired字段关联
        System.out.println(bean.userHolder);
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }
}
