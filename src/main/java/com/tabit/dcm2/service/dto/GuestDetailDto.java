package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.IBeanValidator;
import com.tabit.dcm2.validation.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestDetailDto extends AbstractBean {

    private Optional<StayDto> stayDto = Optional.empty();
    private Optional<GuestPersonalDetailsDto> guestPersonalDetails = Optional.empty();
    private List<StaySummaryDto> staySummaries = new ArrayList<>();

    public Optional<StayDto> getStayDto() {
        return stayDto;
    }

    public Optional<GuestPersonalDetailsDto> getGuestPersonalDetails() {
        return guestPersonalDetails;
    }

    public List<StaySummaryDto> getStaySummaries() {
        return staySummaries;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<GuestDetailDto, Builder> {

        public Builder() {
            super(new GuestDetailDto());
            addValidators(new IBeanValidator() {
                @Override
                public ValidationResult validate() {
                    return (bean.stayDto.isPresent() && bean.guestPersonalDetails.isPresent()) || (!bean.stayDto.isPresent() && !bean.guestPersonalDetails.isPresent())
                            ? ValidationResult.withError(GuestDetailDto.class.getSimpleName(), "Exactly one of stayDto or guestPersonalDetails must be set")
                            : ValidationResult.noError();
                }
            });
        }

        public Builder withStayDto(StayDto stayDto) {
            bean.stayDto = Optional.ofNullable(stayDto);
            return this;
        }

        public Builder withStaySummaries(List<StaySummaryDto> staySummaries) {
            bean.staySummaries = staySummaries;
            return this;
        }

        public Builder withGuestPersonalDetailsDto(GuestPersonalDetailsDto guestPersonalDetailsDto) {
            bean.guestPersonalDetails = Optional.ofNullable(guestPersonalDetailsDto);
            return this;
        }
    }
}
