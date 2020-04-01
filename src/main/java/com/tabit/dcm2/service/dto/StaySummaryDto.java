package com.tabit.dcm2.service.dto;

import java.time.LocalDate;

public class StaySummaryDto {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public StaySummaryDto(Long id, LocalDate checkInDate, LocalDate checkOutDate) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}