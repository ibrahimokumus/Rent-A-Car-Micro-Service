package com.kodlamaoi.invoiceService.business.abstracts;

import java.util.List;

import com.kodlamaoi.invoiceService.business.response.GetAllInvoicesResponse;
import com.kodlamaoi.invoiceService.entity.Invoice;

public interface InvoiceService {

	void add(Invoice invoice);
    List<GetAllInvoicesResponse> getAll();
}
