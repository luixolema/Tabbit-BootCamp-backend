package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomUser {
    public static User createRandomUser() {
        ValueProvider valueProvider = new ValueProvider();
        User user = new User();
        user.setId(valueProvider.randomId());
        user.setName(valueProvider.randomString("name"));
        user.setLogin(valueProvider.randomEmail());
        user.setPassword(valueProvider.randomString("password"));

        return user;
    }

    public static User createRandomUserWithoutId() {
        User user = createRandomUser();
        user.setId(null);

        return user;
    }
}
