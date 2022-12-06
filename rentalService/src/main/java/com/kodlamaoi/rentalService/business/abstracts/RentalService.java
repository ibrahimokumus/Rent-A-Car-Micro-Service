package com.kodlamaoi.rentalService.business.abstracts;

import java.util.List;

import com.kodlamaoi.common.request.CreatePaymentRequest;
import com.kodlamaoi.rentalService.business.request.CreateRentalRequest;
import com.kodlamaoi.rentalService.business.request.UpdateRentalRequest;
import com.kodlamaoi.rentalService.business.response.CreateRentalResponse;
import com.kodlamaoi.rentalService.business.response.GetAllRentalResponse;
import com.kodlamaoi.rentalService.business.response.UpdateRentalResponse;

public interface RentalService {

	CreateRentalResponse add(CreateRentalRequest createRentalRequest,CreatePaymentRequest createPaymentRequest);
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest, String id);
	List<GetAllRentalResponse> getAll();
	void delete(String id);
}
