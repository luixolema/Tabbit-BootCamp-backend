package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.NotEmptyOptionalFieldValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("PMD.ImmutableField")
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

    public StayDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<StayDto, StayDto.Builder> {

        public Builder() {
            super(new StayDto());
            String simpleName = StayDto.class.getSimpleName() + "." + GuestPersonalDetailsDto.class.getSimpleName();

            this.addValidators(
                    new NotEmptyOptionalFieldValidator(simpleName + ".passportId", () -> bean.getGuestPersonalDetails().getPassportId()),
                    new NotEmptyOptionalFieldValidator(simpleName + ".phone", () -> bean.getGuestPersonalDetails().getPhone())
            );
        }

        public static Builder builderFromBean(StayDto toCopy) {
            return new Builder()
                    .withGuestPersonalDetails(toCopy.getGuestPersonalDetails().copy())
                    .withStayDetails(toCopy.getStayDetails().copy())
                    .withLoanDetails(toCopy.getLoanDetails().stream().map(LoanDetailsDto::copy).collect(Collectors.toList()));
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
}

