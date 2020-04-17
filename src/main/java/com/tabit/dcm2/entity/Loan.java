package com.tabit.dcm2.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan implements IEntity {
    @Id
    @Column(name = "id")
    @TableGenerator(allocationSize = 1,
            name = "IDGenerator",
            table = "id_gen",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "loan_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IDGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(name = "day_out", nullable = false)
    private LocalDate dayOut;

    @Column(name = "day_return", nullable = true)
    private LocalDate dayReturn;

    @Column(name = "price", nullable = false)
    private Double price;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
