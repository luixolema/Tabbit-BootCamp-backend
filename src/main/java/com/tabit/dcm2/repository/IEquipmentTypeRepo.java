package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipmentTypeRepo extends JpaRepository<EquipmentType, Long> {
}
