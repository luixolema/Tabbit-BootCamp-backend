package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomEquipment {

    public static Equipment createRandomEquipmentWithoutId() {
        Equipment equipment = createRandomEquipment();
        equipment.setId(null);
        equipment.setEquipmentType(RandomEquipmentType.createEquipmentTypeWithoutId());
        return equipment;
    }

    public static Equipment createRandomEquipment() {
        ValueProvider valueProvider = new ValueProvider();

        Equipment equipment = new Equipment();
        equipment.setId(valueProvider.randomId());
        equipment.setSerialNumber(valueProvider.randomString("serial_num_"));
        equipment.setStatus(valueProvider.randomString("status_"));
        equipment.setEquipmentType(RandomEquipmentType.createEquipmentType());
        return equipment;
    }
}
