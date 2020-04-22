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

    @Column(name = "date_out", nullable = false)
    private LocalDate dateOut;

    @Column(name = "date_return", nullable = true)
    private LocalDate dateReturn;

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

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDate dateReturn) {
        this.dateReturn = dateReturn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
