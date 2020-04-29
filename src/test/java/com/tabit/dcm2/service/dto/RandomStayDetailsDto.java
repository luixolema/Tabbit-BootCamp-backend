package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.testutils.ValueProvider;

import java.time.LocalDate;

public class RandomStayDetailsDto {
    public static StayDetailsDto createRandomStayDetailsDto() {
        return createRandomStayDetailsDtoBuilder().build();
    }

    public static StayDetailsDto.Builder createRandomStayDetailsDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();
        LocalDate checkInDate = LocalDate.now();
        Integer randomValue = valueProvider.randomIntBetween(1, 10);
        LocalDate arriveDate = checkInDate.minusDays(randomValue);
        LocalDate leaveDate = checkInDate.plusDays(randomValue);
        LocalDate checkOutDate = valueProvider.randomBoolean() ? leaveDate.minusDays(1) : null;

        return new StayDetailsDto.Builder()
                .withId(valueProvider.randomId())
                .withBoxNumber(valueProvider.randomString("boxNumber"))
                .withCheckInDate(checkInDate)
                .withCheckOutDate(checkOutDate)
                .withArriveDate(arriveDate)
                .withLeaveDate(leaveDate)
                .withHotel(valueProvider.randomString("hotel"))
                .withRoom(valueProvider.randomString("room"))
                .withLastDiveDate(arriveDate)
                .withBrevet(valueProvider.randomString("brevet"))
                .withDivesAmount(valueProvider.randomPositiveIntUnder100())
                .withNitrox(valueProvider.randomBoolean())
                .withMedicalStatement(valueProvider.randomBoolean())
                .withPreBooking(valueProvider.randomString("prebooking"));
    }

    public static StayDetailsDto createRandomStayDetailsDtoFromStay(Stay stay) {
        return createRandomStayDetailsDtoBuilderFromStay(stay).build();
    }

    public static StayDetailsDto.Builder createRandomStayDetailsDtoBuilderFromStay(Stay stay) {
        return new StayDetailsDto.Builder()
                .withId(stay.getId())
                .withBoxNumber(stay.getBoxNumber())
                .withCheckInDate(stay.getCheckInDate())
                .withCheckOutDate(stay.getCheckOutDate())
                .withArriveDate(stay.getArriveDate())
                .withLeaveDate(stay.getLeaveDate())
                .withHotel(stay.getHotel())
                .withRoom(stay.getRoom())
                .withLastDiveDate(stay.getLastDiveDate())
                .withBrevet(stay.getBrevet())
                .withDivesAmount(stay.getDivesAmount())
                .withNitrox(stay.isNitrox())
                .withMedicalStatement(stay.isMedicalStatement())
                .withPreBooking(stay.getPreBooking());
    }
}
