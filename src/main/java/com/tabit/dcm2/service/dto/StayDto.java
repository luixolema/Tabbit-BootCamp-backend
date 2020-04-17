package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.controller.util.MapperUtil;
import com.tabit.dcm2.entity.Loan;

import java.util.ArrayList;
import java.util.List;

public class StayDto extends AbstractBean {

    private GuestPersonalDetailsDto guestPersonalDetails;
    private StayDetailsDto stayDetails;
    private List<LoanDetailsDto> loanDetails = new ArrayList<>();

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

    public List<LoanDetailsDto> getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(List<LoanDetailsDto> loanDetails) {
        this.loanDetails = loanDetails;
    }

    public void addLoanDetails(Loan loan) {
        loanDetails.add(MapperUtil.mapLoanToLoanDetailsDto(loan));
    }
}

