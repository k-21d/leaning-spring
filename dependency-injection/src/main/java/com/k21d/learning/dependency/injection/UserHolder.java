package com.k21d.learning.dependency.injection;

import com.k21d.learning.spring.ioc.domain.User;

public class UserHolder {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserHolder(User user) {
        this.user = user;
    }
    public UserHolder() {
    }
    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
