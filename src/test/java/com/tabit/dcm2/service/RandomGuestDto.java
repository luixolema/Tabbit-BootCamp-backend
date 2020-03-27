package com.tabit.dcm2.service;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuestDto {
    public static GuestDto createRandomGuestDto() {
        ValueProvider valueProvider = new ValueProvider();

        GuestDto guestDto = new GuestDto();
        guestDto.setId(valueProvider.randomId());
        guestDto.setFirstName(valueProvider.randomString("firstname"));
        guestDto.setLastName(valueProvider.randomString("lastname"));
        guestDto.setCheckedin(valueProvider.randomBoolean());

        return guestDto;
    }
}