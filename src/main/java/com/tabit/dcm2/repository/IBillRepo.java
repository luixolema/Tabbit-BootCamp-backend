package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepo extends JpaRepository<Bill, Long> {
}
