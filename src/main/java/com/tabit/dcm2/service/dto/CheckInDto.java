package com.tabit.dcm2.service.dto;

public class CheckInDto {
    private GuestPersonalDetailsDto guestPersonalDetails;
    private StayDetailsWithoutIdDto stayDetails;

    public GuestPersonalDetailsDto getGuestPersonalDetails() {
        return guestPersonalDetails;
    }

    public void setGuestPersonalDetails(GuestPersonalDetailsDto guestPersonalDetailsDto) {
        this.guestPersonalDetails = guestPersonalDetailsDto;
    }

    public StayDetailsWithoutIdDto getStayDetails() {
        return stayDetails;
    }

    public void setStayDetails(StayDetailsWithoutIdDto stayDetails) {
        this.stayDetails = stayDetails;
    }
}
