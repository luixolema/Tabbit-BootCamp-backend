package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepo extends JpaRepository<Invoice, Long> {

}
