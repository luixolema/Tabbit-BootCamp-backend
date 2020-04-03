package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomStay {

    public static Stay createRandomStayWithoutId() {
        Stay stay = createRandomStay();
        stay.setId(null);
        return stay;
    }

    public static Stay createRandomStay() {
        ValueProvider valueProvider = new ValueProvider();

        Stay stay = new Stay();
        stay.setId(valueProvider.randomId());
        stay.setFirstName(valueProvider.randomString("firstname"));
        stay.setLastName(valueProvider.randomString("lastname"));
        stay.setBoxNumber(valueProvider.randomInt().toString());
        stay.setBirthDate(valueProvider.randomLocalDate());
        stay.setNationality(valueProvider.randomString("nationality"));
        stay.setCountry(valueProvider.randomString("country"));
        stay.setCity(valueProvider.randomString("city"));
        stay.setPostcode(valueProvider.randomString("postcode"));
        stay.setStreet(valueProvider.randomString("street"));
        stay.setEmail(valueProvider.randomString("email"));
        stay.setPhone(valueProvider.randomString("phone"));
        stay.setPassportId(valueProvider.randomString("passport"));
        stay.setCheckInDate(valueProvider.randomLocalDate());
        stay.setCheckOutDate(valueProvider.randomLocalDate());
        stay.setArriveDate(valueProvider.randomLocalDate());
        stay.setLeaveDate(valueProvider.randomLocalDate());
        stay.setHotel(valueProvider.randomString("hotel"));
        stay.setRoom(valueProvider.randomString("room"));
        stay.setLastDiveDate(valueProvider.randomLocalDate());
        stay.setBrevet(valueProvider.randomString("brevet"));
        stay.setDivesAmount(valueProvider.randomInt());
        stay.setNitrox(valueProvider.randomBoolean());
        stay.setMedicalStatement(valueProvider.randomBoolean());
        stay.setPreBoocking(valueProvider.randomString("pre-boocking"));

        return stay;
    }
}
