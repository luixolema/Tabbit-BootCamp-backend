package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.entity.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
        loanDetails.add(MAP_LOAN_TO_LOAN_DETAILS_DTO.apply(loan));
    }

    private Function<Loan, LoanDetailsDto> MAP_LOAN_TO_LOAN_DETAILS_DTO = loan -> {
        LoanDetailsDto loanDetailsDto = new LoanDetailsDto();
        loanDetailsDto.setId(loan.getId());
        loanDetailsDto.setDateOut(loan.getDateOut());
        loanDetailsDto.setDateReturn(loan.getDateReturn());
        loanDetailsDto.setSerialNumber(loan.getEquipment().getSerialNumber());
        loanDetailsDto.setType(loan.getEquipment().getEquipmentType().getType());
        return loanDetailsDto;
    };
}

