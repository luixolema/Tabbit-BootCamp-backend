package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

import java.time.LocalDate;

public class RandomStayDetailsForCheckInDto {
    public static StayDetailsForCheckInDto createRandomStayDetailsForCheckInDto() {
        return createRandomStayDetailsForCheckInDtoBuilder().build();
    }

    public static StayDetailsForCheckInDto.Builder createRandomStayDetailsForCheckInDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();
        LocalDate checkInDate = LocalDate.now();
        Integer randomValue = valueProvider.randomIntBetween(1, 10);
        LocalDate arriveDate = checkInDate.minusDays(randomValue);
        LocalDate leaveDate = checkInDate.plusDays(randomValue);
        LocalDate lastDiveDate = checkInDate.minusDays(randomValue);

        return new StayDetailsForCheckInDto.Builder()
                .withArriveDate(arriveDate)
                .withBoxNumber(valueProvider.randomString("boxNumber"))
                .withBrevet(valueProvider.randomString("brevet"))
                .withCheckInDate(checkInDate)
                .withDivesAmount(valueProvider.randomPositiveIntUnder100())
                .withHotel(valueProvider.randomString("hotel"))
                .withLastDiveDate(lastDiveDate)
                .withLeaveDate(leaveDate)
                .withMedicalStatement(valueProvider.randomBoolean())
                .withNitrox(valueProvider.randomBoolean())
                .withPreBooking(valueProvider.randomString("prebooking"))
                .withRoom(valueProvider.randomString("room"));
    }
}
