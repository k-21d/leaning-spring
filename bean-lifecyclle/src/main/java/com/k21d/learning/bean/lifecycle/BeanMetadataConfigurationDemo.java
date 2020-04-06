package com.k21d.learning.bean.lifecycle;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/**
 * Bean元信息配置实例
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int beanNums = beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/user.properties");
        System.out.println("已经加载的BeanDefinition数量："+beanNums);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
