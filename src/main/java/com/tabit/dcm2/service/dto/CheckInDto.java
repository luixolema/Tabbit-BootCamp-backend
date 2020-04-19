package com.tabit.dcm2.service.dto;

public class CheckInDto {
    private GuestPersonalDetailsDto guestPersonalDetails;
    private StayDetailsForCheckInDto stayDetails;

    public GuestPersonalDetailsDto getGuestPersonalDetails() {
        return guestPersonalDetails;
    }

    public void setGuestPersonalDetails(GuestPersonalDetailsDto guestPersonalDetailsDto) {
        this.guestPersonalDetails = guestPersonalDetailsDto;
    }

    public StayDetailsForCheckInDto getStayDetails() {
        return stayDetails;
    }

    public void setStayDetails(StayDetailsForCheckInDto stayDetails) {
        this.stayDetails = stayDetails;
    }
}
