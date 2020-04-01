package com.k21d.learning.spring.bean.definition;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id",44);
        beanDefinitionBuilder.addPropertyValue("name","json");
        //获取 BeanDefinition 实例
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition并非Bean终态，可以自定义修改
        //2.通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("id",4);
        propertyValues.addPropertyValue("name","kvein");
        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
