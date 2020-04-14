package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStayRepo extends JpaRepository<Stay, Long> {
    @Query("SELECT s.boxNumber FROM Stay s WHERE s.active = true")
    List<String> getBoxNumbers();
}
