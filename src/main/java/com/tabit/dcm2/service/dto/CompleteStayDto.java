package com.tabit.dcm2.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CompleteStayDto extends StayDto {

    private List<LoanDto> loans = new ArrayList<>();
    // the activities now can be added here // todo: remove this line after added the activities dtos

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

    public void setLoanDtos(List<LoanDto> loans) {
        this.loans = loans;
    }
}

