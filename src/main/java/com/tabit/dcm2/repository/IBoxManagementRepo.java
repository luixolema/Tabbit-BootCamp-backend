package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.BoxManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBoxManagementRepo extends JpaRepository<BoxManagement, Long> {
    Optional<BoxManagement> findByBoxNumber(String boxNumber);
}
