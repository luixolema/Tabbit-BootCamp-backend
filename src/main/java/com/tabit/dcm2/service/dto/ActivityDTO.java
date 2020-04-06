package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Activity;

import java.time.LocalDate;

public class ActivityDTO {
    private Long id;

    private String pos;

    private Activity.ActivityType type;

    private LocalDate date;

    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Activity.ActivityType getType() {
        return type;
    }

    public void setType(Activity.ActivityType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}