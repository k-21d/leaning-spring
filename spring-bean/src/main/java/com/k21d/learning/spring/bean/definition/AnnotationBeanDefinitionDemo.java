package com.k21d.learning.spring.bean.definition;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 注解BeanDefinition方式
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class配置类
        applicationContext.register(Config.class);
        //1.通过@Bean方式定义
        //2.@Component方式
        //3.@Import导入
        applicationContext.refresh();
        Map<String, Config> configMap = applicationContext.getBeansOfType(Config.class);
        System.out.println(configMap);
        Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
        System.out.println(userMap);
        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     * @param registry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry,String beanName,Class<?> beanClass){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id",2L).addPropertyValue("name","yy");
        registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
    }

    public static void registerBeanDeinition(BeanDefinitionRegistry registry,Class<?> beanClass){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id",2L).addPropertyValue("name","yy");
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
    }

    @Component
    public static class Config{
        @Bean(name = {"user","my-user"})
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("xxx");
            return user;
        }
    }

}
