package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Guest;
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

    public static GuestPersonalDetailsDto createGuestPersonalDetailsDtoFromGuest(Guest guest) {
        GuestPersonalDetailsDto guestPersonalDetailsDto = new GuestPersonalDetailsDto();

        guestPersonalDetailsDto.setId(guest.getId());
        guestPersonalDetailsDto.setFirstName(guest.getFirstName());
        guestPersonalDetailsDto.setLastName(guest.getLastName());
        guestPersonalDetailsDto.setBirthDate(guest.getBirthDate());
        guestPersonalDetailsDto.setEmail(guest.getEmail());
        guestPersonalDetailsDto.setNationality(guest.getNationality());
        guestPersonalDetailsDto.setCity(guest.getCity());
        guestPersonalDetailsDto.setPassportId(guest.getPassportId());
        guestPersonalDetailsDto.setPhone(guest.getPhone());
        guestPersonalDetailsDto.setCountry(guest.getCountry());
        guestPersonalDetailsDto.setPostcode(guest.getPostcode());
        guestPersonalDetailsDto.setStreet(guest.getStreet());

        return guestPersonalDetailsDto;
    }
}
