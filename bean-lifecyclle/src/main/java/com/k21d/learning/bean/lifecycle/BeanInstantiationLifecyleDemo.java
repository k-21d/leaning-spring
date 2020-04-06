package com.k21d.learning.bean.lifecycle;

import com.k21d.learning.spring.ioc.domain.SuperUser;
import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

public class BeanInstantiationLifecyleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        ClassPathResource classPathResource = new ClassPathResource("META-INF/dependency-lookup-context.xml");
        beanDefinitionReader.loadBeanDefinitions(classPathResource);
        User bean = beanFactory.getBean("user",User.class);
        System.out.println(bean);
    }
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser",beanName) && SuperUser.class.equals(beanClass)){
                return new SuperUser();
            }
            return null;//保持Spring IoC容器实例化操作
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user",beanName)&&User.class.equals(bean.getClass())){
                //“user”对象不允许属性赋值
                User user = (User)bean;
                user.setName("xxx");
                return false;
            }
            return true;
        }
    }
}
