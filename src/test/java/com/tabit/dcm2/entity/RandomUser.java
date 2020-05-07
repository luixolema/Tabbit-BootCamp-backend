package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RandomUser {
    public static User createRandomUser() {
        ValueProvider valueProvider = new ValueProvider();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setId(valueProvider.randomId());
        user.setName(valueProvider.randomString("name"));
        user.setLogin(valueProvider.randomEmail());
        user.setPassword(passwordEncoder.encode(valueProvider.randomString("password")));

        return user;
    }

    public static User createRandomUserWithoutId() {
        User user = createRandomUser();
        user.setId(null);

        return user;
    }
}
