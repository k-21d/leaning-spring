package com.k21d.learning.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        //1.获取HierarchicalBeanFactory<-ConfigurableBeanFactory<-ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当亲BeanFactory的Parent BeanFactory："+beanFactory.getParentBeanFactory());
        //2.设置Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当亲BeanFactory的Parent BeanFactory："+beanFactory.getParentBeanFactory());
        displayLocalBean(beanFactory,"user");

        displayLocalBean(parentBeanFactory,"user");

        displayContainsBean(beanFactory,"user");
        displayContainsBean(parentBeanFactory,"user");
        applicationContext.refresh();
        applicationContext.close();
    }
    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName){
        boolean flag = containsBean(beanFactory, beanName);
        System.out.printf("当前BeanFactory[%s]是否包含Bean[name:%s]\n",beanFactory,beanName,flag);

    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory,String beanName){
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory,beanName)) return true;
        }
        return beanFactory.containsLocalBean(beanName);
    }
    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName){
        System.out.printf("当前BeanFactory[%s]是否包含LocalBean[name:%s]\n",beanFactory,beanName,beanFactory.containsLocalBean(beanName));
    }
    private static HierarchicalBeanFactory createBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
