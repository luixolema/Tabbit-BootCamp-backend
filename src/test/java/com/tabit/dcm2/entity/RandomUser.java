package com.tabit.dcm2.entity;

import com.tabit.dcm2.service.dto.LoginDto;
import com.tabit.dcm2.testutils.ValueProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RandomUser {
    public static User createRandomUserWithPassword(String password) {
        ValueProvider valueProvider = new ValueProvider();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setId(valueProvider.randomId());
        user.setDiveCenter(RandomDiveCenter.createRandomDiveCenter());
        user.setName(valueProvider.randomString("name"));
        user.setLogin(valueProvider.randomEmail());
        user.setPassword(passwordEncoder.encode(password));

        return user;
    }

    public static User createRandomUse() {
        ValueProvider valueProvider = new ValueProvider();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setId(valueProvider.randomId());
        user.setDiveCenter(RandomDiveCenter.createRandomDiveCenter());
        user.setName(valueProvider.randomString("name"));
        user.setLogin(valueProvider.randomEmail());
        user.setPassword(passwordEncoder.encode(valueProvider.randomString("password")));

        return user;
    }

    public static User createRandomUserWithPasswordWithoutId(String password) {
        User user = createRandomUserWithPassword(password);
        user.setId(null);

        return user;
    }

    public static User createRandomUserFromLoginDto(LoginDto loginDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = createRandomUserWithPasswordWithoutId(loginDto.getPassword());

        user.setLogin(loginDto.getLogin());
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));

        return user;
    }

    public static User createRandomUserGivenDiveCenter(DiveCenter diveCenter) {
        User user = createRandomUse();
        user.setDiveCenter(diveCenter);
        return user;
    }

    public static User createRandomUserWithoutIdGivenDiveCenter(DiveCenter diveCenter) {
        User user = createRandomUse();
        user.setDiveCenter(diveCenter);
        user.setId(null);
        return user;
    }
}
