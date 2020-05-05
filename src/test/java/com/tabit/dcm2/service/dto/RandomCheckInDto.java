package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomCheckInDto {
    public static CheckInDto createRandomCheckInDto() {
        return createRandomCheckInDtoBuilder().build();
    }

    public static CheckInDto.Builder createRandomCheckInDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();

        return new CheckInDto.Builder()
                .withGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder()
                        .withPhone(valueProvider.randomString("phone"))
                        .withPassportId(valueProvider.randomString("passport"))
                        .build())
                .withStayDetails(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDto());
    }
}
