package com.tabit.dcm2.service.dto;

import java.time.LocalDate;

public class StaySummaryDto {

    private Long id;
    private LocalDate arriveDate;
    private LocalDate leaveDate;

    public StaySummaryDto(Long id, LocalDate arriveDate, LocalDate leaveDate) {
        this.id = id;
        this.arriveDate = arriveDate;
        this.leaveDate = leaveDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }
}