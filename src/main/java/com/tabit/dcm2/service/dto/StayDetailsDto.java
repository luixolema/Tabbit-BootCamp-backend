package com.tabit.dcm2.service.dto;

import java.time.LocalDate;

public class StayDetailsDto extends AbstractStayDetailsDto {
    private Long id;
    private LocalDate checkOutDate;
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}