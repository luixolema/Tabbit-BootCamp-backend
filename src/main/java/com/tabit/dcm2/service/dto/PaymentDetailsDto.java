package com.tabit.dcm2.service.dto;

import java.time.LocalDate;

public class PaymentDetailsDto {
    private String activityType;
    private LocalDate date;
    private Long price;

    public PaymentDetailsDto(String activityType, LocalDate date, Long price) {
        this.activityType = activityType;
        this.date = date;
        this.price = price;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
