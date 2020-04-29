package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuestDto {
    public static GuestDto createRandomGuestDto() {
        return createRandomGuestDtoBuilder().build();
    }

    public static GuestDto.Builder createRandomGuestDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();
        boolean checkedin = valueProvider.randomBoolean();
        String boxNumber = checkedin ? valueProvider.randomString("boxNumber") : null;
        return new GuestDto.Builder()
                .withId(valueProvider.randomId())
                .withFirstName(valueProvider.randomString("firstname"))
                .withLastName(valueProvider.randomString("lastname"))
                .withCheckedin(checkedin)
                .withBoxNumber(boxNumber);
    }
}
