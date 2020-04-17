package com.tabit.dcm2.entity;

public class EquipmentRule extends AbstractDbRule<Equipment> {
    @Override
    protected String getEntitySimpleName() {
        return Equipment.class.getSimpleName();
    }
}
