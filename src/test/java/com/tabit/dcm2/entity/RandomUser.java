package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomUser {
    public static User createRandomUserWithoutId() {
        ValueProvider valueProvider = new ValueProvider();
        User user = new User();
        user.setName(valueProvider.randomString("name"));
        user.setLogin(valueProvider.randomString("login"));
        user.setPassword(valueProvider.randomString("password"));

        return user;
    }
}
