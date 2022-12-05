package com.kodlamaoi.paymentService.business.abstracts;

import java.util.List;

import com.kodlamaoi.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaoi.paymentService.business.response.CreatePaymentResponse;
import com.kodlamaoi.paymentService.business.response.GetAllPaymentResponse;

public interface PaymentService {

	
	List<GetAllPaymentResponse> getAll();
    CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest);
	
}
