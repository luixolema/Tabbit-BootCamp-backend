package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomStay {
    public static Stay createRandomStay() {
        ValueProvider valueProvider = new ValueProvider();

        Guest guest = RandomGuest.createRandomGuestWitoutId();

        Stay stay = new Stay();
        stay.setGuest(guest);
        stay.setFirstName(valueProvider.randomString("firstname"));
        stay.setLastName(valueProvider.randomString("lastname"));
        stay.setBoxId(valueProvider.randomId());
        stay.setBirthDate(valueProvider.randomDate());
        stay.setCity(valueProvider.randomString("city"));
        stay.setCountry(valueProvider.randomString("country"));
        stay.setNationality(valueProvider.randomString("nationality"));
        stay.setPostcode(valueProvider.randomString("postcode"));
        stay.setStreet(valueProvider.randomString("street"));
        stay.setEmail(valueProvider.randomString("email"));
        stay.setPhone(valueProvider.randomString("phone"));
        stay.setPassportId(valueProvider.randomString("passport"));
        stay.setCheckInDate(valueProvider.randomDate());
        stay.setCheckOutDate(valueProvider.randomDate());
        stay.setArriveDate(valueProvider.randomDate());
        stay.setLeaveDate(valueProvider.randomDate());
        stay.setHotel(valueProvider.randomString("hotel"));
        stay.setRoom(valueProvider.randomString("room"));
        stay.setLastDiveDate(valueProvider.randomDate());
        stay.setBrevet(valueProvider.randomString("Brevet"));
        stay.setDivesAmount(valueProvider.randomInt());
        stay.setNitrox(valueProvider.randomBoolean());
        stay.setMedicalStatement(valueProvider.randomBoolean());
        return stay;
    }
}
