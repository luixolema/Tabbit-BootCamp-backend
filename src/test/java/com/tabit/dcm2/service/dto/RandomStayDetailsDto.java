package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

import java.time.LocalDate;

public class RandomStayDetailsDto {
    public static StayDetailsDto createRandomStayDetailsDto() {
        ValueProvider valueProvider = new ValueProvider();
        LocalDate checkInDate = LocalDate.now();
        Integer randomValue = valueProvider.randomIntBetween(1, 10);
        LocalDate arriveDate = checkInDate.minusDays(randomValue);
        LocalDate leaveDate = arriveDate.minusDays(randomValue);

        StayDetailsDto stayDetailsDto = new StayDetailsDto();
        stayDetailsDto.setId(valueProvider.randomId());
        stayDetailsDto.setArriveDate(arriveDate);
        stayDetailsDto.setBoxNumber(valueProvider.randomString("boxNumber"));
        stayDetailsDto.setBrevet(valueProvider.randomString("brevet"));
        stayDetailsDto.setCheckInDate(valueProvider.randomLocalDate());
        stayDetailsDto.setCheckOutDate(valueProvider.randomLocalDate());
        stayDetailsDto.setDivesAmount(valueProvider.randomInt());
        stayDetailsDto.setHotel(valueProvider.randomString("hotel"));
        stayDetailsDto.setLastDiveDate(valueProvider.randomLocalDate());
        stayDetailsDto.setLeaveDate(leaveDate);
        stayDetailsDto.setMedicalStatement(valueProvider.randomBoolean());
        stayDetailsDto.setNitrox(valueProvider.randomBoolean());
        stayDetailsDto.setPreBooking(valueProvider.randomString("prebooking"));
        stayDetailsDto.setRoom(valueProvider.randomString("room"));

        return stayDetailsDto;
    }
}
