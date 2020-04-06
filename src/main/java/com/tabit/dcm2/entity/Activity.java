package com.tabit.dcm2.entity;

import java.time.LocalDate;

/**
 * I do not mark this class as entity in orther to not change all the test an the db structure
 */
public class Activity {
    private Long id;

    private String pos;

    private ActivityType type;

    private LocalDate date;

    private Double price;

    public enum ActivityType
    {
        TYPE_1, TYPE_2, TYPE_3
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
