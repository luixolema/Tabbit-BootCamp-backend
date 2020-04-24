package com.tabit.dcm2.entity;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.testutils.ValueProvider;

import java.time.LocalDate;

public class RandomStay {

    public static Stay createRandomStayWithoutId() {
        Stay stay = createRandomStay();
        stay.setId(null);
        stay.setLoans(ImmutableList.of(RandomLoan.createRandomLoanWithoutId()));
        return stay;
    }

    public static Stay createRandomStayWithoutIdGivenActiveState(boolean active) {
        Stay stay = createRandomStayWithoutId();
        stay.setActive(active);
        return stay;
    }

    public static Stay createRandomStay() {
        ValueProvider valueProvider = new ValueProvider();
        LocalDate checkInDate = LocalDate.now();
        Integer randomValue = valueProvider.randomIntBetween(1, 10);
        LocalDate arriveDate = checkInDate.minusDays(randomValue);
        LocalDate leaveDate = arriveDate.minusDays(randomValue);

        Stay stay = new Stay();
        stay.setId(valueProvider.randomId());
        stay.setFirstName(valueProvider.randomString("firstname"));
        stay.setLastName(valueProvider.randomString("lastname"));
        stay.setActive(valueProvider.randomBoolean());
        stay.setBoxNumber(valueProvider.randomPositiveIntUnder100().toString());
        stay.setBirthDate(valueProvider.randomLocalDate());
        stay.setNationality(valueProvider.randomString("nationality"));
        stay.setCountry(valueProvider.randomString("country"));
        stay.setCity(valueProvider.randomString("city"));
        stay.setPostcode(valueProvider.randomString("postcode"));
        stay.setStreet(valueProvider.randomString("street"));
        stay.setEmail(valueProvider.randomString("email") + "@mail.com");
        stay.setPhone(valueProvider.randomString("phone"));
        stay.setPassportId(valueProvider.randomString("passport"));
        stay.setCheckInDate(checkInDate);
        stay.setCheckOutDate(valueProvider.randomLocalDate());
        stay.setArriveDate(arriveDate);
        stay.setLeaveDate(leaveDate);
        stay.setHotel(valueProvider.randomString("hotel"));
        stay.setRoom(valueProvider.randomString("room"));
        stay.setLastDiveDate(valueProvider.randomLocalDate());
        stay.setBrevet(valueProvider.randomString("brevet"));
        stay.setDivesAmount(valueProvider.randomPositiveIntUnder100());
        stay.setNitrox(valueProvider.randomBoolean());
        stay.setMedicalStatement(valueProvider.randomBoolean());
        stay.setPreBooking(valueProvider.randomString("pre-booking"));
        stay.setLoans(ImmutableList.of(RandomLoan.createRandomLoan()));

        return stay;
    }
}
