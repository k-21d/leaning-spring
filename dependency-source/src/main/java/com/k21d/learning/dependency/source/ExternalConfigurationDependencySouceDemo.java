package com.k21d.learning.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
@Configuration
@PropertySource("META-INF/default.properties")
public class ExternalConfigurationDependencySouceDemo {
    @Value("${user.id}")
    private Long id;

    @Value("${user.resource}")
    private Resource resource;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencySouceDemo.class);

        applicationContext.refresh();

        ExternalConfigurationDependencySouceDemo bean = applicationContext.getBean(ExternalConfigurationDependencySouceDemo.class);
        System.out.println(bean.id);
        System.out.println(bean.resource);
        applicationContext.close();
    }
}
