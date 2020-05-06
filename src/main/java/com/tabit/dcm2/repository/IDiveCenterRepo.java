package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.DiveCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiveCenterRepo extends JpaRepository<DiveCenter, Long> {

}
