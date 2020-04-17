package com.tabit.dcm2.entity;

import javax.persistence.*;

@Entity
@Table(
        name = "equipment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"serial_number", "equipment_type_id"})}
)
public class Equipment implements IEntity {
    @Id
    @Column(name = "id")
    @TableGenerator(allocationSize = 1,
            name = "IDGenerator",
            table = "id_gen",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "equipment_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IDGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_type_id")
    private EquipmentType equipmentType;

    @Column(name = "serial_number", nullable = false)
    private String SerialNumber;

    @Column(name = "status", nullable = false)
    private EquipmentStatus status;

    public enum EquipmentStatus {
        AVAILABLE, STOLEN, BROKEN, IN_REPAIR
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }
}
