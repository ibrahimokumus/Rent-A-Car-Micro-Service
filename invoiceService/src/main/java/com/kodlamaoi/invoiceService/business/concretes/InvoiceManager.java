package com.kodlamaoi.invoiceService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaoi.common.utilities.mapping.ModelMapperService;
import com.kodlamaoi.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaoi.invoiceService.business.response.GetAllInvoicesResponse;
import com.kodlamaoi.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaoi.invoiceService.entity.Invoice;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor

public class InvoiceManager implements InvoiceService{

	private ModelMapperService modelMapperService;
	private InvoiceRepository invoiceRepository;
	
	@Override
	public void add(Invoice invoice) {
		
		invoice.setId(UUID.randomUUID().toString());
		invoiceRepository.save(invoice);
	}

	@Override
	public List<GetAllInvoicesResponse> getAll() {
		List<Invoice> invoices= invoiceRepository.findAll();
		List<GetAllInvoicesResponse> response = invoices.stream().map(invoice->modelMapperService.forResponse().map(invoices, GetAllInvoicesResponse.class)).collect(Collectors.toList());
		
		return response;
	}
	
	
	
}
