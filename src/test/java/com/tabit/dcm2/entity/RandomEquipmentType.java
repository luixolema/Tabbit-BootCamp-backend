package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomEquipmentType {

    public static EquipmentType createRandomEquipmentTypeWithoutId() {
        EquipmentType equipmentType = createRandomEquipmentType();
        equipmentType.setId(null);
        return equipmentType;
    }

    public static EquipmentType createRandomEquipmentType() {
        ValueProvider valueProvider = new ValueProvider();

        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setId(valueProvider.randomId());
        equipmentType.setDiveCenter(RandomDiveCenter.createRandomDiveCenter());
        equipmentType.setType(valueProvider.randomString("type_"));
        equipmentType.setDescription(valueProvider.randomString("description_"));
        equipmentType.setActive(valueProvider.randomBoolean());
        equipmentType.setPrice(valueProvider.randomDouble());

        return equipmentType;
    }

    public static EquipmentType createRandomEquipmentTypeWithoutIdGivenDiveCenter(DiveCenter diveCenter) {
        EquipmentType equipmentType = createRandomEquipmentTypeGivenDiveCenter(diveCenter);
        equipmentType.setId(null);

        return equipmentType;
    }

    public static EquipmentType createRandomEquipmentTypeGivenDiveCenter(DiveCenter diveCenter) {

        EquipmentType equipmentType = createRandomEquipmentType();
        equipmentType.setDiveCenter(diveCenter);

        return equipmentType;

    }
}
