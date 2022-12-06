package com.kodlamaoi.paymentService.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaoi.common.request.CreatePaymentRequest;
import com.kodlamaoi.paymentService.business.abstracts.PaymentService;
import com.kodlamaoi.paymentService.business.request.PaymentRequest;
import com.kodlamaoi.paymentService.business.response.CreatePaymentResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

	private PaymentService service;

    @PostMapping
    public CreatePaymentResponse add(@RequestBody CreatePaymentRequest request) {
        return service.add(request);
    }

    @PostMapping("/check")
    public void checkIfPaymentSuccessful(@RequestParam String cardNumber, @RequestParam String fullName,
             @RequestParam String cardCvv, @RequestParam double price) {
        PaymentRequest request = new PaymentRequest(cardNumber, fullName, cardCvv, price);
        service.checkIfPaymentSuccessful(request);
    }
}
