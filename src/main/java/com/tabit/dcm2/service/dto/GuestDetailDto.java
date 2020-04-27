package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

import java.util.ArrayList;
import java.util.List;

public class GuestDetailDto extends AbstractBean {

    private StayDto stayDto;
    private List<StaySummaryDto> staySummaries = new ArrayList<>();

    public StayDto getStayDto() {
        return stayDto;
    }

    public List<StaySummaryDto> getStaySummaries() {
        return staySummaries;
    }

//    public void addStaySummary(Stay stay) {
//        staySummaries.add(new StaySummaryDto.Builder()
//                .withId(stay.getId())
//                .withArriveDate(stay.getArriveDate())
//                .withLeaveDate(stay.getLeaveDate())
//                .withActive(stay.isActive())
//                .build());
//    }


    public GuestDetailDto copy() {

        return new GuestDetailDto.Builder()
                .withStayDto(getStayDto().copy())
                .withStaySummaries(prepareStaySummaryDtos(this))
                .build();
    }

    private static List<StaySummaryDto> prepareStaySummaryDtos(GuestDetailDto guestDetailDto) {
        List<StaySummaryDto> staySummaryDtos = new ArrayList<>();
        for (StaySummaryDto dto : guestDetailDto.getStaySummaries()) {
            staySummaryDtos.add(StaySummaryDto.Builder.builderFromBean(dto).build());
        }
        return staySummaryDtos;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<GuestDetailDto, Builder> {

        public Builder() {
            super(new GuestDetailDto());
        }

        public static Builder builderFromBean(GuestDetailDto toCopy) {


            return new Builder()
                    .withStayDto(toCopy.getStayDto().copy())
                    .withStaySummaries(prepareStaySummaryDtos(toCopy));
        }

        public Builder withStayDto(StayDto stayDto) {
            bean.stayDto = stayDto;
            return this;
        }

        public Builder withStaySummaries(List<StaySummaryDto> staySummaries) {
            bean.staySummaries = staySummaries;
            return this;
        }
    }
}
