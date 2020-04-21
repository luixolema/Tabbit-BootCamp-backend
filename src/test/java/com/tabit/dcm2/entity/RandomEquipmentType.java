package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomEquipmentType {

    public static EquipmentType createRandomEquipmentType() {
        ValueProvider valueProvider = new ValueProvider();
        EquipmentType equipmentType = new EquipmentType();

        equipmentType.setId(valueProvider.randomId());
        equipmentType.setActive(valueProvider.randomBoolean());
        equipmentType.setDescription(valueProvider.randomString("description"));
        equipmentType.setPrice(valueProvider.randomPrice());
        equipmentType.setType(valueProvider.randomString("type"));

        return equipmentType;
    }

    public static EquipmentType createRandomEquipmentTypeWithoutId() {
        EquipmentType equipmentType = createRandomEquipmentType();
        equipmentType.setId(null);

        return equipmentType;
    }
}
