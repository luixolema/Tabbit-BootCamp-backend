package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.testutils.ValueProvider;

public class RandomLoginDto {
    public static LoginDto.Builder createRandomLoginDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();

        return new LoginDto.Builder()
                .withLogin(valueProvider.randomEmail())
                .withPassword(valueProvider.randomString("password"));
    }

    public static LoginDto createRandomLoginDto() {
        return createRandomLoginDtoBuilder().build();
    }

    public static LoginDto createRandomLoginDtoFromUser(User user) {
        return new LoginDto.Builder()
                .withLogin(user.getLogin())
                .withPassword(user.getPassword())
                .build();
    }
}
