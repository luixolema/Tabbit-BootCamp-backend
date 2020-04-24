package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

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
