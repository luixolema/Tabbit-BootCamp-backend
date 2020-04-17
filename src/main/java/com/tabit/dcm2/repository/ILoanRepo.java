package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanRepo extends JpaRepository<Loan, Long> {
}
