package com.k21d.learning.bean.lifecycle;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AnnotatedBeanDefinitionReader definitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int before = beanFactory.getBeanDefinitionCount();
        //注册当前类
        definitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int after = beanFactory.getBeanDefinitionCount();
        System.out.println("已加载BeanDefinition数量："+(after-before));
        AnnotatedBeanDefinitionParsingDemo definitionParsingDemo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(definitionParsingDemo);
    }
}
