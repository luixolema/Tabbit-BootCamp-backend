package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.NotEmptyOptionalFieldValidator;

public class CheckInDto extends AbstractBean {
    private GuestPersonalDetailsDto guestPersonalDetails;
    private StayDetailsForCheckInDto stayDetails;

    public GuestPersonalDetailsDto getGuestPersonalDetails() {
        return guestPersonalDetails;
    }

    public StayDetailsForCheckInDto getStayDetails() {
        return stayDetails;
    }

    public CheckInDto copy() {
        return new Builder()
                .withStayDetails(getStayDetails().copy())
                .withGuestPersonalDetails(getGuestPersonalDetails().copy())
                .build();
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<CheckInDto, Builder> {

        public Builder() {
            super(new CheckInDto());

            String simpleName = CheckInDto.class.getSimpleName() + "." + GuestPersonalDetailsDto.class.getSimpleName();

            this.addValidators(
                    new NotEmptyOptionalFieldValidator(simpleName + ".passportId", () -> bean.getGuestPersonalDetails().getPassportId()),
                    new NotEmptyOptionalFieldValidator(simpleName + ".phone", () -> bean.getGuestPersonalDetails().getPhone())
            );
        }

        public Builder withStayDetails(StayDetailsForCheckInDto stayDetails) {
            bean.stayDetails = stayDetails;
            return this;
        }

        public Builder withGuestPersonalDetails(GuestPersonalDetailsDto guestPersonalDetailsDto) {
            bean.guestPersonalDetails = guestPersonalDetailsDto;
            return this;
        }
    }
}
