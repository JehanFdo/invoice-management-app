package com.nttdata.invoicemanagementapp.repository;

import com.nttdata.invoicemanagementapp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice  i WHERE i.invoiceNo = ?1")
    Optional<Invoice> findByInvoiceNo(String invoiceNo);
}
