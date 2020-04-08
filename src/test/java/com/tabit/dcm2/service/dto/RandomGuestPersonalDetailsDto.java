package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuestPersonalDetailsDto {
    public static GuestPersonalDetailsDto createRandomGuestPersonalDetailsDto() {
        ValueProvider valueProvider = new ValueProvider();

        GuestPersonalDetailsDto guestPersonalDetailsDto = new GuestPersonalDetailsDto();
        guestPersonalDetailsDto.setId(valueProvider.randomId());
        guestPersonalDetailsDto.setBirthDate(valueProvider.randomLocalDate());
        guestPersonalDetailsDto.setFirstName(valueProvider.randomString("firstName"));
        guestPersonalDetailsDto.setCity(valueProvider.randomString("city"));
        guestPersonalDetailsDto.setCountry(valueProvider.randomString("country"));
        guestPersonalDetailsDto.setEmail(valueProvider.randomString("email"));
        guestPersonalDetailsDto.setLastName(valueProvider.randomString("lastName"));
        guestPersonalDetailsDto.setNationality(valueProvider.randomString("nationality"));
        guestPersonalDetailsDto.setPassportId(valueProvider.randomString("passportId"));
        guestPersonalDetailsDto.setPhone(valueProvider.randomString("phone"));
        guestPersonalDetailsDto.setPostcode(valueProvider.randomString("postcode"));
        guestPersonalDetailsDto.setStreet(valueProvider.randomString("street"));

        return guestPersonalDetailsDto;
    }
}
