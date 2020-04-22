package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomEquipmentType {

    public static EquipmentType createEquipmentTypeWithoutId() {
        EquipmentType equipmentType = createEquipmentType();
        equipmentType.setId(null);
        return equipmentType;
    }

    public static EquipmentType createEquipmentType() {
        ValueProvider valueProvider = new ValueProvider();

        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setId(valueProvider.randomId());
        equipmentType.setType(valueProvider.randomString("type_"));
        equipmentType.setDescription(valueProvider.randomString("description_"));
        equipmentType.setActive(valueProvider.randomBoolean());
        equipmentType.setPrice(valueProvider.randomDouple());

        return equipmentType;
    }
}
