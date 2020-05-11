package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EquipmentRepoDbTest extends AbstractDbTest {
    @Autowired
    private IEquipmentRepo equipmentRepo;

    private Equipment equipment;
    private Equipment equipment2;
    private EquipmentType equipmentType;
    private DiveCenter diveCenter;

    @Before
    public void setUp() {
        // given
        diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        equipment = RandomEquipment.createRandomEquipmentWithoutIdGivenDiveCenter(diveCenter);
        equipmentType = equipment.getEquipmentType();

        equipmentRule.persist(ImmutableList.of(equipment));
    }

    @Test
    public void findById_shall_return_the_equipment() {
        //when
        Optional<Equipment> actualEquipment = equipmentRepo.findById(equipment.getId());

        //then
        assertThat(actualEquipment.isPresent()).isTrue();
        assertEquipment(actualEquipment.get(), equipment);
    }

    @Test
    public void save_shall_save_the_equipment() {
        //given
        equipment2 = RandomEquipment.createRandomEquipmentWithoutIdGivenDiveCenter(diveCenter);

        //when
        equipmentRepo.save(equipment2);

        //then
        Optional<Equipment> actualEquipment = equipmentRepo.findById(equipment2.getId());

        assertThat(actualEquipment.isPresent()).isTrue();
        assertEquipment(actualEquipment.get(), equipment2);
    }

    private void assertEquipment(Equipment actualEquipment, Equipment expectedEquipment) {
        assertThat(actualEquipment.getId()).isEqualTo(expectedEquipment.getId());
        assertThat(actualEquipment.getSerialNumber()).isEqualTo(expectedEquipment.getSerialNumber());
        assertThat(actualEquipment.getEquipmentType().getId()).isEqualTo(expectedEquipment.getEquipmentType().getId());
        assertThat(actualEquipment.getStatus()).isEqualTo(expectedEquipment.getStatus());
        assertThat(actualEquipment.getDiveCenter().getId()).isEqualTo(expectedEquipment.getDiveCenter().getId());
    }
}
