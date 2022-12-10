package com.kodlamaoi.invoiceService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaoi.invoiceService.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

}
