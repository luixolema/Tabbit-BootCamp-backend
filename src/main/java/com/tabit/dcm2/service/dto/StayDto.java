package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;

import java.util.ArrayList;
import java.util.List;

public class StayDto extends AbstractBean {

    private GuestPersonalDetailsDto guestPersonalDetails;
    private StayDetailsDto stayDetails;
    private List<LoanDto> loans = new ArrayList<>();

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

    public List<LoanDto> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDto> loans) {
        this.loans = loans;
    }
}

