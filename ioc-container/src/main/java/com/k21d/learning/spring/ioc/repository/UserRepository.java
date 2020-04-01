package com.k21d.learning.spring.ioc.repository;

import com.k21d.learning.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

public class UserRepository {
    private Collection<User> users; //自定义Bean

    private BeanFactory beanFactory;    //内建非Bean对象(依赖)

    private ObjectFactory<ApplicationContext> objectFactory;

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public Collection<User> getUsers(){
        return users;
    }
    public void setUsers(Collection<User> users){
        this.users = users;
    }

}
