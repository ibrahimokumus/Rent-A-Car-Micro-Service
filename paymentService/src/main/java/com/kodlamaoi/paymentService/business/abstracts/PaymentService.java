package com.kodlamaoi.paymentService.business.abstracts;

import com.kodlamaoi.common.request.CreatePaymentRequest;
import com.kodlamaoi.paymentService.business.request.PaymentRequest;
import com.kodlamaoi.paymentService.business.response.CreatePaymentResponse;

public interface PaymentService {

	 CreatePaymentResponse add(CreatePaymentRequest request);
	 void checkIfPaymentSuccessful(PaymentRequest request);
	
}
