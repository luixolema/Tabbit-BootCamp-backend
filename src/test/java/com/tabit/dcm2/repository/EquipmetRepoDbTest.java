package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Equipment;
import com.tabit.dcm2.entity.EquipmentType;
import com.tabit.dcm2.entity.RandomEquipment;
import com.tabit.dcm2.entity.RandomEquipmentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EquipmetRepoDbTest extends AbstractDbTest {
    @Autowired
    private IEquipmentRepo equipmentRepo;

    private Equipment equipmentInDb;

    @Before
    public void setUp() {
        EquipmentType randomEquipmentType = RandomEquipmentType.createRandomEquipmentTypeWitoutId();
        equipmentTypeRule.persist(ImmutableList.of(randomEquipmentType));

        equipmentInDb = RandomEquipment.createRandomEquipmentWitoutId();
        equipmentInDb.setEquipmentType(randomEquipmentType);
        equipmentRule.persist(ImmutableList.of(equipmentInDb));
    }

    @Test
    public void save_shall_save_the_equipment() {
        //given
        EquipmentType randomEquipmentType = RandomEquipmentType.createRandomEquipmentTypeWitoutId();
        equipmentTypeRule.persist(ImmutableList.of(randomEquipmentType));
        Equipment randomEquipment = RandomEquipment.createRandomEquipmentWitoutId();
        randomEquipment.setEquipmentType(randomEquipmentType);

        //when
        equipmentRepo.save(randomEquipment);

        //then
        Optional<Equipment> actualStay = equipmentRepo.findById(randomEquipment.getId());

        assertThat(actualStay.isPresent()).isTrue();
        assertEquipment(actualStay.get(), randomEquipment);
    }

    private void assertEquipment(Equipment equipment, Equipment expectedEquipment) {
        assertThat(equipment.getEquipmentType().getId()).isEqualTo(expectedEquipment.getEquipmentType().getId());
        assertThat(equipment.getSerialNumber()).isEqualTo(expectedEquipment.getSerialNumber());
        assertThat(equipment.getStatus()).isEqualTo(expectedEquipment.getStatus());
        assertThat(equipment.getId()).isEqualTo(expectedEquipment.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void save_shall_throw_exception_for_an_existing_combination_serialNumber_equipmentType() {

        //given
        Equipment randomEquipment = RandomEquipment.createRandomEquipmentWitoutId();
        randomEquipment.setSerialNumber(equipmentInDb.getSerialNumber());
        randomEquipment.setEquipmentType(equipmentInDb.getEquipmentType());

        //when
        equipmentRepo.save(randomEquipment);
    }
}
