package com.wtytest.helper.builder;

import com.wtytest.entity.User;

/**
 * Created by tywang on 28/06/2017.
 */
public class UserBuilder {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder withDefault(){
        user.setAge(18);
        user.setName("wty");
        return this;
    }

    public UserBuilder withAge(Integer age){
        user.setAge(age);
        return this;
    }
    public UserBuilder withName(String name){
        user.setName(name);
        return this;
    }
    public User build(){
        return user;
    }
}
