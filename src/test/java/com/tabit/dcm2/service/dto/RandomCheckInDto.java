package com.tabit.dcm2.service.dto;

public class RandomCheckInDto {
    public static CheckInDto createRandomCheckInDto() {
        CheckInDto checkInDto = new CheckInDto();
        checkInDto.setGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto());
        checkInDto.setStayDetails(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDto());
        return checkInDto;
    }
}
