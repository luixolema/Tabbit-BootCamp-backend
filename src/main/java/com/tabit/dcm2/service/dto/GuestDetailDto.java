package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.entity.Stay;

import java.util.ArrayList;
import java.util.List;

public class GuestDetailDto extends AbstractBean {

    private CompleteStayDto stayDto;
    private List<StaySummaryDto> staySummaries = new ArrayList<>();

    public CompleteStayDto getStayDto() {
        return stayDto;
    }

    public void setCompleteStayDto(CompleteStayDto completeStayDto) {
        this.stayDto = completeStayDto;
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
