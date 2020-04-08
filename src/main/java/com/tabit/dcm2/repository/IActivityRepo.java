package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivityRepo extends JpaRepository<Activity, Long> {

}
