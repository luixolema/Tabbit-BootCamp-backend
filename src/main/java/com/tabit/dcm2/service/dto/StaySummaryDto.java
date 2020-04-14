package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;

import java.time.LocalDate;

public class StaySummaryDto extends AbstractBean {

    private Long id;
    private LocalDate arriveDate;
    private LocalDate leaveDate;
    private boolean active;

    public StaySummaryDto(Long id, LocalDate arriveDate, LocalDate leaveDate, boolean active) {
        this.id = id;
        this.arriveDate = arriveDate;
        this.leaveDate = leaveDate;
        this.active = active;
    }

    public StaySummaryDto() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}