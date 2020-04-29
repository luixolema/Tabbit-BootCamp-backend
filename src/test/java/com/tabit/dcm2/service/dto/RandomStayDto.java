package com.tabit.dcm2.service.dto;

import com.google.common.collect.ImmutableList;

public class RandomStayDto {
    public static StayDto createRandomStayDto() {
        return createRandomStayDtoBuilder().build();
    }

    public static StayDto.Builder createRandomStayDtoBuilder() {
        return new StayDto.Builder()
                .withGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto())
                .withStayDetails(RandomStayDetailsDto.createRandomStayDetailsDto())
                .withLoanDetails(ImmutableList.of(RandomLoanDetailsDto.createRandomLoanDetailsDto()));
    }
}
