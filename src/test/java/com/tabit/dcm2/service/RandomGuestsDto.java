package com.tabit.dcm2.service;

import com.google.common.collect.ImmutableList;

public class RandomGuestsDto {
    public static GuestOverviewDto createRandomGuestsDto() {
        GuestDto randomGuestDto1 = RandomGuestDto.createRandomGuestDto();
        GuestDto randomGuestDto2 = RandomGuestDto.createRandomGuestDto();
        return new GuestOverviewDto(ImmutableList.of(randomGuestDto1, randomGuestDto2));
    }
}