package com.kodlamaoi.paymentService.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentRequest {

	
	 private String cardNumber;
	    private String fullName;
	    private String cardCvv;
	    private double price;
}
