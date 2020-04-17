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

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipment_item_id")
    private EquipmentItem item;

    @Column(name = "date_out", nullable = false)
    private LocalDate dateOut;

    @Column(name = "date_return")
    private LocalDate dateReturn;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EquipmentItem getItem() {
        return item;
    }

    public void setItem(EquipmentItem item) {
        this.item = item;
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
}
