package com.kodlamaoi.paymentService.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kodlamaoi.paymentService.business.abstracts.PaymentService;
import com.kodlamaoi.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaoi.paymentService.business.response.CreatePaymentResponse;

public class PaymentController {

	private PaymentService paymentService;
	@PostMapping
	public CreatePaymentResponse add(@RequestBody CreatePaymentRequest createPaymentRequest)
	{
		return paymentService.add(createPaymentRequest);
	}
}
