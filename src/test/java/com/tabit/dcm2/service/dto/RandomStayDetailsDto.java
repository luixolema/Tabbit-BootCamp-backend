package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomStayDetailsDto {
    public static StayDetailsDto createRandomStayDetailsDto() {
        ValueProvider valueProvider = new ValueProvider();

        StayDetailsDto stayDetailsDto = new StayDetailsDto();
        stayDetailsDto.setId(valueProvider.randomId());
        stayDetailsDto.setArriveDate(valueProvider.randomLocalDate());
        stayDetailsDto.setBoxNumber(valueProvider.randomString("boxNumber"));
        stayDetailsDto.setBrevet(valueProvider.randomString("brevet"));
        stayDetailsDto.setCheckInDate(valueProvider.randomLocalDate());
        stayDetailsDto.setCheckOutDate(valueProvider.randomLocalDate());
        stayDetailsDto.setDivesAmount(valueProvider.randomInt());
        stayDetailsDto.setHotel(valueProvider.randomString("hotel"));
        stayDetailsDto.setLastDiveDate(valueProvider.randomLocalDate());
        stayDetailsDto.setLeaveDate(valueProvider.randomLocalDate());
        stayDetailsDto.setMedicalStatement(valueProvider.randomBoolean());
        stayDetailsDto.setNitrox(valueProvider.randomBoolean());
        stayDetailsDto.setPreBooking(valueProvider.randomString("prebooking"));
        stayDetailsDto.setRoom(valueProvider.randomString("room"));

        return stayDetailsDto;
    }
}
