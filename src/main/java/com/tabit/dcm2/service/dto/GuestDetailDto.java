package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Stay;

import java.util.ArrayList;
import java.util.List;

public class GuestDetailDto {

    private StayDto stayDto;
    private List<StaySummaryDto> staySummaries = new ArrayList<>();

    public StayDto getStayDto() {
        return stayDto;
    }

    public void setStayDto(StayDto stayDto) {
        this.stayDto = stayDto;
    }

    public List<StaySummaryDto> getStaySummaries() {
        return staySummaries;
    }

    public void setStaySummaries(List<StaySummaryDto> staySummaries) {
        this.staySummaries = staySummaries;
    }

    public void addStaySummary(Stay stay) {
        staySummaries.add(new StaySummaryDto(stay.getId(), stay.getArriveDate(), stay.getLeaveDate(), stay.isActive()));
    }
}
