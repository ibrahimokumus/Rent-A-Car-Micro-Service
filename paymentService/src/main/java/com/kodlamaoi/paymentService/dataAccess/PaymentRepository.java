package com.kodlamaoi.paymentService.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaoi.paymentService.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String>{

	 boolean existsByCardNumberAndFullNameAndCardCvv(
	    String cardNumber, String fullName, String cardCvv);
	    Payment findByCardNumber(String cardNumber);
}
