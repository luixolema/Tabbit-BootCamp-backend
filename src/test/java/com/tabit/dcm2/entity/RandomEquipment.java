package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomEquipment {

    public static Equipment createRandomEquipment() {
        ValueProvider valueProvider = new ValueProvider();

        Equipment equipment = new Equipment();
        equipment.setId(valueProvider.randomId());
        equipment.setSerialNumber(valueProvider.randomString("serialNumber"));
        equipment.setStatus(Equipment.EquipmentStatus.AVAILABLE);
        equipment.setEquipmentType(RandomEquipmentType.createRandomEquipmentType());

        return equipment;
    }

    public static Equipment createRandomEquipmentWithoutId() {
        Equipment equipment = createRandomEquipment();
        equipment.setEquipmentType(null);
        equipment.setId(null);

        return equipment;
    }
}
