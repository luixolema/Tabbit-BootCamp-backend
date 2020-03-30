package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStayRepo extends JpaRepository<Stay, Long> {
    List<Stay> findByGuest(Guest guest);
}
