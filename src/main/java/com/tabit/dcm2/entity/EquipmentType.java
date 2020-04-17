package com.tabit.dcm2.entity;

import javax.persistence.*;

@Entity
public class EquipmentType implements IEntity {
    @Id
    @Column(name = "id")
    @TableGenerator(allocationSize = 1,
            name = "IDGenerator",
            table = "id_gen",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "equipment_type_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IDGenerator")
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Override
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
