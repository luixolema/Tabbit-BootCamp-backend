package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomStayDetailsForCheckInDto {
    public static StayDetailsForCheckInDto createRandomStayDetailsForCheckInDto() {
        ValueProvider valueProvider = new ValueProvider();

        StayDetailsForCheckInDto stayDetailsDto = new StayDetailsForCheckInDto();
        stayDetailsDto.setArriveDate(valueProvider.randomLocalDate());
        stayDetailsDto.setBoxNumber(valueProvider.randomString("boxNumber"));
        stayDetailsDto.setBrevet(valueProvider.randomString("brevet"));
        stayDetailsDto.setCheckInDate(valueProvider.randomLocalDate());
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
