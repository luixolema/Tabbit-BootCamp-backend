package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomEquipment {

    public static Equipment createRandomEquipmentWithoutId() {
        Equipment equipment = createRandomEquipment();
        equipment.setId(null);
        equipment.setEquipmentType(RandomEquipmentType.createRandomEquipmentTypeWithoutId());
        return equipment;
    }

    public static Equipment createRandomEquipment() {
        ValueProvider valueProvider = new ValueProvider();

        Equipment equipment = new Equipment();
        equipment.setId(valueProvider.randomId());
        equipment.setDiveCenter(RandomDiveCenter.createRandomDiveCenter());
        equipment.setSerialNumber(valueProvider.randomString("serial_num_"));
        equipment.setStatus(valueProvider.randomString("status_"));
        equipment.setEquipmentType(RandomEquipmentType.createRandomEquipmentType());

        return equipment;
    }

    public static Equipment createRandomEquipmentWithoutIdGivenDiveCenter(DiveCenter diveCenter) {
        Equipment equipment = createRandomEquipment();
        equipment.setDiveCenter(diveCenter);
        equipment.setEquipmentType(RandomEquipmentType.createRandomEquipmentTypeWithoutIdGivenDiveCenter(diveCenter));
        equipment.setId(null);
        return equipment;

    }

    public static Equipment createRandomEquipmentGivenDiveCenter(DiveCenter diveCenter) {
        Equipment equipment = createRandomEquipment();
        equipment.setDiveCenter(diveCenter);
        equipment.setEquipmentType(RandomEquipmentType.createRandomEquipmentTypeGivenDiveCenter(diveCenter));

        return equipment;
    }
}
