package com.kodlamaio.inventoryService.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryService.business.request.create.CreateCarRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateCarResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllCarResponse;
import com.kodlamaio.inventoryService.business.response.get.GetCarResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateCarResponse;

public interface CarService {
	List<GetAllCarResponse> getAll();

	GetCarResponse getById(String id);

	CreateCarResponse add(CreateCarRequest request);

	UpdateCarResponse update(UpdateCarRequest request, String id);

	void delete(String id);

	void changeCarState(String id, int state);

	void carAvialibleState(String carId);

}
