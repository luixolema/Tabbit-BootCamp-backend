package com.tabit.dcm2.service.dto;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuestDetailDto {
    public static GuestDetailDto createRandomGuestDetailDto() {
        return createRandomGuestDetailDtoBuilder().build();
    }

    public static GuestDetailDto.Builder createRandomGuestDetailDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();
        boolean checkedIn = valueProvider.randomBoolean();

        return new GuestDetailDto.Builder()
                .withGuestPersonalDetailsDto(!checkedIn ? RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto() : null)
                .withStayDto(checkedIn ? RandomStayDto.createRandomStayDto() : null)
                .withStaySummaries(ImmutableList.of(RandomStaySummaryDto.createRandomStaySummaryDto()));
    }
}
