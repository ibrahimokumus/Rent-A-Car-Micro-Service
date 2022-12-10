package com.kodlamaoi.invoiceService.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaoi.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaoi.invoiceService.business.response.GetAllInvoicesResponse;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
	private InvoiceService invoiceService;
	
	
	@GetMapping
	public List<GetAllInvoicesResponse> getAll(){
		return invoiceService.getAll();
	}
	
}
