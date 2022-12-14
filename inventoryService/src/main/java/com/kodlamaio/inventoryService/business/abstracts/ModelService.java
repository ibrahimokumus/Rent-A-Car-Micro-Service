package com.kodlamaio.inventoryService.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryService.business.request.create.CreateModelRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateModelResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllModelResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateModelResponse;

public interface ModelService {

	List<GetAllModelResponse> getAll();
	CreateModelResponse add(CreateModelRequest createModelRequest);
	void delete(String id);
	UpdateModelResponse update(UpdateModelRequest updateModelRequest);
}
