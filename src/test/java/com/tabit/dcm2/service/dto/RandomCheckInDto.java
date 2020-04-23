package com.tabit.dcm2.service.dto;

public class RandomCheckInDto {
    public static CheckInDto createRandomCheckInDto() {
        return createRandomCheckInDtoBuilder().build();
    }

    public static CheckInDto.Builder createRandomCheckInDtoBuilder() {
        return new CheckInDto.Builder()
                .withGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto())
                .withStayDetails(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDto());
    }
}
