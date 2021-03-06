package com.tabit.dcm2.entity;

import javax.persistence.*;

@Entity
public class BoxManagement implements IEntity {
    @Id
    @Column(name = "id")
    @TableGenerator(allocationSize = 1,
            name = "IDGenerator",
            table = "id_gen",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "box_management_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IDGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dive_center_id")
    private DiveCenter diveCenter;

    @Column(name = "box_number", nullable = false)
    private String boxNumber;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiveCenter getDiveCenter() {
        return diveCenter;
    }

    public void setDiveCenter(DiveCenter diveCenter) {
        this.diveCenter = diveCenter;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }
}
