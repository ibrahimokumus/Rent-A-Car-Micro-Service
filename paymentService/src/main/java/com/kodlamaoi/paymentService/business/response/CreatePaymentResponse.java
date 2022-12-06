package com.kodlamaoi.paymentService.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreatePaymentResponse {

	 private String id;
	    private String cardNumber;
	    private String fullName;
	    private String cardCvv;
	    private double balance;
}
