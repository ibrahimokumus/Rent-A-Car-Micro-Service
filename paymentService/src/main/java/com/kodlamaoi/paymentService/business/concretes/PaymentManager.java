package com.kodlamaoi.paymentService.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaoi.common.request.CreatePaymentRequest;
import com.kodlamaoi.common.utilities.exceptions.BusinessException;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;
import com.kodlamaoi.paymentService.business.abstracts.PaymentService;
import com.kodlamaoi.paymentService.business.abstracts.PosService;
import com.kodlamaoi.paymentService.business.request.PaymentRequest;
import com.kodlamaoi.paymentService.business.response.CreatePaymentResponse;
import com.kodlamaoi.paymentService.dataAccess.PaymentRepository;
import com.kodlamaoi.paymentService.entity.Payment;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService mapper;
    private final PosService posService;


    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(UUID.randomUUID().toString());
        repository.save(payment);
        CreatePaymentResponse response = mapper.forResponse().map(payment, CreatePaymentResponse.class);
        return response;
    }

    @Override
    public void checkIfPaymentSuccessful(PaymentRequest request) {
        checkPayment(request);
    }

    private void checkPayment(PaymentRequest request) {
        if (!repository.existsByCardNumberAndFullNameAndCardCvv(request.getCardNumber(), request.getFullName(), request.getCardCvv())) {
            throw new BusinessException("Invalid payment");
        }
        double balance = repository.findByCardNumber(request.getCardNumber()).getBalance();
        if (balance < request.getPrice()) {
            throw new BusinessException("No enough money");
        }
        posService.pay(); // Fake payment
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        payment.setBalance(balance - request.getPrice());
        repository.save(payment);
    }

}