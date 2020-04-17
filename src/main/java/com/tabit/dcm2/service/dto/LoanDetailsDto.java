package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;

import java.time.LocalDate;

public class LoanDetailsDto extends AbstractBean {
    private Long id;
    private String type;
    private String serialNumber;
    private LocalDate dayOut;
    private LocalDate dayReturn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getDayOut() {
        return dayOut;
    }

    public void setDayOut(LocalDate dayOut) {
        this.dayOut = dayOut;
    }

    public LocalDate getDayReturn() {
        return dayReturn;
    }

    public void setDayReturn(LocalDate dayReturn) {
        this.dayReturn = dayReturn;
    }
}