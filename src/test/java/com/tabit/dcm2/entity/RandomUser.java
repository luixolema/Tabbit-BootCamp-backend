package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomUser {
    public static User createRandomUser() {
        ValueProvider valueProvider = new ValueProvider();
        User user = new User();
        user.setId(valueProvider.randomId());
        user.setDiveCenter(RandomDiveCenter.createRandomDiveCenter());
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

    public static User createRandomUserGivenDiveCenter(DiveCenter diveCenter) {
        User user = createRandomUser();
        user.setDiveCenter(diveCenter);
        return user;
    }

    public static User createRandomUserWithoutIdGivenDiveCenter(DiveCenter diveCenter) {
        User user = createRandomUser();
        user.setDiveCenter(diveCenter);
        user.setId(null);
        return user;
    }
}
