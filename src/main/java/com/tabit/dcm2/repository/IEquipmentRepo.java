package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEquipmentRepo extends JpaRepository<Equipment, Long> {
}
