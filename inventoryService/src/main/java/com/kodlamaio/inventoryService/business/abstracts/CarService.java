package com.kodlamaio.inventoryService.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryService.business.request.create.CreateCarRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateCarResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllCarResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateCarResponse;

public interface CarService {
	List<GetAllCarResponse> getAll();
	CreateCarResponse add(CreateCarRequest createCarRequest);
	void delete(String id);
	
	UpdateCarResponse update(UpdateCarRequest updateCarRequest);
	//public void changeState(int state,String id);
	void changeCarState(String oldCarId,String newCarId);
	void changeCarState(String id);
	GetAllCarResponse getById(String carId);
	
	
}
