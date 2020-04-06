package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Activity;

import java.util.List;

public class StayDto {

    private GuestPersonalDetailsDto guestPersonalDetails;
    private StayDetailsDto stayDetails;
    private List<ActivityDTO> activities;

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

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }
}

