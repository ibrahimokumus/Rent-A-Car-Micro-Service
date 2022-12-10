package com.kodlamaoi.invoiceService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaoi.common.events.InvoiceCreatedEvent;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;
import com.kodlamaoi.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaoi.invoiceService.entity.Invoice;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RentalConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
	private ModelMapperService modelMapperService;
	private InvoiceService invoiceService;

	@KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "invoiceCreated"
    )
	public void consume(InvoiceCreatedEvent event) {
		LOGGER.info(String.format("order event received in invoice-service -> %s",event.toString()));
		Invoice invoice = modelMapperService.forRequest().map(event,Invoice.class);
		invoiceService.add(invoice);
		
		LOGGER.info("invoice created");
	
	}


}
