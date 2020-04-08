package com.tabit.dcm2.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Activity {

    @Id
    @TableGenerator(allocationSize = 1,
            name = "IDGenerator",
            table = "id_gen",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "activity_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IDGenerator")
    private Long id;

    @Column
    private String pos;

    @Column
    private ActivityType type;

    @Column
    private LocalDate date;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    public enum ActivityType
    {
        TYPE_1, TYPE_2, TYPE_3
    }

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

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
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

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }
}
