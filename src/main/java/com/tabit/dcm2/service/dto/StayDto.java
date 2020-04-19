package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;

public class StayDto extends AbstractBean {

    protected GuestPersonalDetailsDto guestPersonalDetails;
    protected StayDetailsDto stayDetails;

    public GuestPersonalDetailsDto getGuestPersonalDetails() {
        return guestPersonalDetails;
    }

    public void setGuestPersonalDetails(GuestPersonalDetailsDto guestPersonalDetailsDto) {
        this.guestPersonalDetails = guestPersonalDetailsDto;
    }

    public StayDetailsDto getStayDetails() {
        return stayDetails;
    }

    public void setStayDetails(StayDetailsDto stayDetails) {
        this.stayDetails = stayDetails;
    }
}

