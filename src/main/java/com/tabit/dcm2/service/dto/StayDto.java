package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
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

    public StayDetailsDto getStayDetails() {
        return stayDetails;
    }

    public List<LoanDetailsDto> getLoanDetails() {
        return loanDetails;
    }

//    public StayDto addLoanDetails(List<Loan> loans) {
//        for(Loan loan : loans) {
//            getLoanDetails().add(MAP_LOAN_TO_LOAN_DETAILS_DTO.apply(loan));
//        }
//        return StayDto.Builder.builderFromBean(this).build();
//    }

    public StayDto copy() {

        return new StayDto.Builder()
                .withGuestPersonalDetails(getGuestPersonalDetails().copy())
                .withStayDetails(getStayDetails().copy())
                .withLoanDetails(prepareLoanDetailsDtos(this))
                .build();
    }

    private static List<LoanDetailsDto> prepareLoanDetailsDtos(StayDto stayDto) {
        List<LoanDetailsDto> loanDetailsDtos = new ArrayList<>();
        for (LoanDetailsDto dto : stayDto.getLoanDetails()) {
            loanDetailsDtos.add(LoanDetailsDto.Builder.builderFromBean(dto).build());
        }
        return loanDetailsDtos;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<StayDto, StayDto.Builder> {

        public Builder() {
            super(new StayDto());
        }

        public static Builder builderFromBean(StayDto toCopy) {
            return new Builder()
                    .withGuestPersonalDetails(toCopy.getGuestPersonalDetails().copy())
                    .withStayDetails(toCopy.getStayDetails().copy())
                    .withLoanDetails(prepareLoanDetailsDtos(toCopy));
        }

        public Builder withGuestPersonalDetails(GuestPersonalDetailsDto guestPersonalDetails) {
            bean.guestPersonalDetails = guestPersonalDetails;
            return this;
        }

        public Builder withStayDetails(StayDetailsDto stayDetails) {
            bean.stayDetails = stayDetails;
            return this;
        }

        public Builder withLoanDetails(List<LoanDetailsDto> loanDetails) {
            bean.loanDetails = loanDetails;
            return this;
        }
    }

    private Function<Loan, LoanDetailsDto> MAP_LOAN_TO_LOAN_DETAILS_DTO = loan -> new LoanDetailsDto.Builder()
            .withId(loan.getId())
            .withDateOut(loan.getDateOut())
            .withDateReturn(loan.getDateReturn())
            .withSerialNumber(loan.getEquipment().getSerialNumber())
            .withType(loan.getEquipment().getEquipmentType().getType())
            .build();
}

