package com.k21d.learning.spring.bean.factory;

import com.k21d.learning.spring.ioc.domain.User;

public interface UserFactory {
    default User createUser(){
        return User.createUser();
    }
}
