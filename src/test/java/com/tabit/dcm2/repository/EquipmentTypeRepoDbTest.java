package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.DiveCenter;
import com.tabit.dcm2.entity.EquipmentType;
import com.tabit.dcm2.entity.RandomDiveCenter;
import com.tabit.dcm2.entity.RandomEquipmentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EquipmentTypeRepoDbTest extends AbstractDbTest {
    @Autowired
    private IEquipmentTypeRepo equipmentTypeRepo;

    private EquipmentType equipmentType;
    private EquipmentType equipmentType2;
    private DiveCenter diveCenter;

    @Before
    public void setUp() {
        // given
        diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        equipmentType = RandomEquipmentType.createRandomEquipmentTypeWithoutIdGivenDiveCenter(diveCenter);

        equipmentTypeRule.persist(ImmutableList.of(equipmentType));
    }

    @Test
    public void findById_shall_return_the_equipment() {
        //when
        Optional<EquipmentType> actualEquipment = equipmentTypeRepo.findById(equipmentType.getId());

        //then
        assertThat(actualEquipment.isPresent()).isTrue();
        assertEquipmentType(actualEquipment.get(), equipmentType);
    }

    @Test
    public void save_shall_save_the_equipment_type() {
        //given
        equipmentType2 = RandomEquipmentType.createRandomEquipmentTypeWithoutIdGivenDiveCenter(diveCenter);

        //when
        equipmentTypeRepo.save(equipmentType2);

        //then
        Optional<EquipmentType> actualEquipmentType = equipmentTypeRepo.findById(equipmentType2.getId());

        assertThat(actualEquipmentType.isPresent()).isTrue();
        assertEquipmentType(actualEquipmentType.get(), equipmentType2);
    }

    private void assertEquipmentType(EquipmentType actualEquipmentType, EquipmentType expectedEquipmentType) {
        assertThat(actualEquipmentType.getId()).isEqualTo(expectedEquipmentType.getId());
        assertThat(actualEquipmentType.getDescription()).isEqualTo(expectedEquipmentType.getDescription());
        assertThat(actualEquipmentType.getPrice()).isEqualTo(expectedEquipmentType.getPrice());
        assertThat(actualEquipmentType.getType()).isEqualTo(expectedEquipmentType.getType());
        assertThat(actualEquipmentType.isActive()).isEqualTo(expectedEquipmentType.isActive());
    }
}
