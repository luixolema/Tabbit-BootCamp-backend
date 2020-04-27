package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuestPersonalDetailsDto {
    public static GuestPersonalDetailsDto createRandomGuestPersonalDetailsDto() {
        return createRandomGuestPersonalDetailsDtoBuilder().build();
    }

    public static GuestPersonalDetailsDto.Builder createRandomGuestPersonalDetailsDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();

        return new GuestPersonalDetailsDto.Builder()
                .withId(valueProvider.randomId())
                .withBirthDate(valueProvider.randomLocalDate())
                .withFirstName(valueProvider.randomString("firstName"))
                .withCity(valueProvider.randomString("city"))
                .withCountry(valueProvider.randomString("country"))
                .withEmail(valueProvider.randomEmail())
                .withLastName(valueProvider.randomString("lastName"))
                .withNationality(valueProvider.randomString("nationality"))
                .withPassportId(valueProvider.randomString("passportId"))
                .withPhone(valueProvider.randomString("phone"))
                .withPostcode(valueProvider.randomString("postcode"))
                .withStreet(valueProvider.randomString("street"));
    }

    public static GuestPersonalDetailsDto createGuestPersonalDetailsDtoFromGuest(Guest guest) {
        return new GuestPersonalDetailsDto.Builder()
                .withId(guest.getId())
                .withFirstName(guest.getFirstName())
                .withLastName(guest.getLastName())
                .withBirthDate(guest.getBirthDate())
                .withEmail(guest.getEmail())
                .withNationality(guest.getNationality())
                .withCity(guest.getCity())
                .withPassportId(guest.getPassportId())
                .withPhone(guest.getPhone())
                .withCountry(guest.getCountry())
                .withPostcode(guest.getPostcode())
                .withStreet(guest.getStreet())
                .build();
    }
}
