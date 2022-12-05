package com.kodlamaoi.paymentService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.kodlamaoi.common.utilities.mapping.ModelMapperService;
import com.kodlamaoi.paymentService.business.abstracts.PaymentService;
import com.kodlamaoi.paymentService.business.request.CreatePaymentRequest;
import com.kodlamaoi.paymentService.business.response.CreatePaymentResponse;
import com.kodlamaoi.paymentService.business.response.GetAllPaymentResponse;
import com.kodlamaoi.paymentService.dataAccess.PaymentRepository;
import com.kodlamaoi.paymentService.entity.Payment;

public class PaymentManager implements PaymentService{

	private PaymentRepository paymentRepository;
	private ModelMapperService modelMapperService;
	@Override
	public List<GetAllPaymentResponse> getAll() {
		
		return paymentRepository.findAll().stream().
                map(payment -> modelMapperService.forResponse().
                        map(payment, GetAllPaymentResponse.class)).collect(Collectors.toList());
	
		
		
	}

	@Override
	public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
		
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        payment.setId(UUID.randomUUID().toString());
        this.paymentRepository.save(payment);
        CreatePaymentResponse createPaymentResponse = this.modelMapperService.forResponse().map(payment,
                CreatePaymentResponse.class);

        return createPaymentResponse;
		
		
	}
	
	

}
