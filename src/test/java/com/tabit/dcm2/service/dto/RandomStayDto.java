package com.tabit.dcm2.service.dto;

public class RandomStayDto {
    public static StayDto createRandomStayDto() {
        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto());
        stayDto.setStayDetails(RandomStayDetailsDto.createRandomStayDetailsDto());
        return stayDto;
    }
}
