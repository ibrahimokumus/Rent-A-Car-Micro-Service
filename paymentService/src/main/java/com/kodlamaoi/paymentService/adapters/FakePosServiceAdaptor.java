package com.kodlamaoi.paymentService.adapters;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.kodlamaoi.common.utilities.exceptions.BusinessException;
import com.kodlamaoi.paymentService.business.abstracts.PosService;

@Service

public class FakePosServiceAdaptor implements PosService {

	@Override
	public void pay() {
		
		 int randomNumber = new Random().nextInt(10);
	        if (randomNumber < 5) {
	            throw new BusinessException("Invalid payment");
	            }
		
	}

}
