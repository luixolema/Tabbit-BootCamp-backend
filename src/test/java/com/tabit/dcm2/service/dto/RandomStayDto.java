package com.tabit.dcm2.service.dto;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.testutils.ValueProvider;

public class RandomStayDto {
    public static StayDto createRandomStayDto() {
        return createRandomStayDtoBuilder().build();
    }

    public static StayDto.Builder createRandomStayDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();

        return new StayDto.Builder()
                .withGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder()
                        .withPhone(valueProvider.randomString("phone"))
                        .withPassportId(valueProvider.randomString("passport"))
                        .build())
                .withStayDetails(RandomStayDetailsDto.createRandomStayDetailsDto())
                .withLoanDetails(ImmutableList.of(RandomLoanDetailsDto.createRandomLoanDetailsDto()));
    }
}
