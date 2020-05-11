package com.tabit.dcm2.entity;

import com.tabit.dcm2.service.dto.LoginDto;
import com.tabit.dcm2.testutils.ValueProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public static User createRandomUserWithPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = createRandomUser();
        user.setPassword(passwordEncoder.encode(password));

        return user;
    }

    public static User createRandomUserWithPasswordWithoutId(String password) {
        User user = createRandomUserWithPassword(password);
        user.setId(null);

        return user;
    }

    public static User createRandomUserFromLoginDto(LoginDto loginDto) {
        User user = createRandomUserWithPassword(loginDto.getPassword());
        user.setLogin(loginDto.getLogin());
        user.setId(null);

        return user;
    }

    public static User createRandomUserGivenDiveCenter(DiveCenter diveCenter) {
        User user = createRandomUser();
        user.setDiveCenter(diveCenter);
        return user;
    }

    public static User createRandomUserWithoutIdGivenDiveCenter(DiveCenter diveCenter) {
        User user = createRandomUserWithoutId();
        user.setDiveCenter(diveCenter);
        return user;
    }
}
