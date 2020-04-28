package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestDetailDto extends AbstractBean {

    private Optional<StayDto> stayDto = Optional.empty();
    private GuestPersonalDetailsDto guestPersonalDetails;
    private List<StaySummaryDto> staySummaries = new ArrayList<>();

    public Optional<StayDto> getStayDto() {
        return stayDto;
    }

    public GuestPersonalDetailsDto getGuestPersonalDetails() {
        return guestPersonalDetails;
    }

    public List<StaySummaryDto> getStaySummaries() {
        return staySummaries;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<GuestDetailDto, Builder> {

        public Builder() {
            super(new GuestDetailDto());
        }

        public Builder withStayDto(Optional<StayDto> stayDto) {
            bean.stayDto = stayDto;
            return this;
        }

        public Builder withStaySummaries(List<StaySummaryDto> staySummaries) {
            bean.staySummaries = staySummaries;
            return this;
        }

        public Builder withGuestPersonalDetailsDto(GuestPersonalDetailsDto guestPersonalDetailsDto) {
            bean.guestPersonalDetails = guestPersonalDetailsDto;
            return this;
        }
    }
}
