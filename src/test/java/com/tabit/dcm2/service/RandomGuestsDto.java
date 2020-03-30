package com.tabit.dcm2.service;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;

public class RandomGuestsDto {
    public static GuestOverviewDto createRandomGuestsDto() {
        GuestDto randomGuestDto1 = RandomGuestDto.createRandomGuestDto();
        GuestDto randomGuestDto2 = RandomGuestDto.createRandomGuestDto();
        return new GuestOverviewDto(ImmutableList.of(randomGuestDto1, randomGuestDto2));
    }
}