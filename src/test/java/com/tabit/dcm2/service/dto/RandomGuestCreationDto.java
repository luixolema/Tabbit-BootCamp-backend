package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuestCreationDto {

    public static GuestCreationDto.Builder createGuestCreationDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();

        return new GuestCreationDto.Builder()
                .withBirthDate(valueProvider.randomLocalDate())
                .withFirstName(valueProvider.randomString("firstName"))
                .withCity(valueProvider.randomString("city"))
                .withCountry(valueProvider.randomString("country"))
                .withEmail(valueProvider.randomEmail())
                .withLastName(valueProvider.randomString("lastName"))
                .withNationality(valueProvider.randomString("nationality"))
                .withPassportId(valueProvider.randomBoolean() ? valueProvider.randomString("passportId") : null)
                .withPhone(valueProvider.randomBoolean() ? valueProvider.randomString("phone") : null)
                .withPostcode(valueProvider.randomString("postcode"))
                .withStreet(valueProvider.randomString("street"));
    }

    public static GuestCreationDto createGuestCreationDto() {
        return createGuestCreationDtoBuilder().build();
    }
}
