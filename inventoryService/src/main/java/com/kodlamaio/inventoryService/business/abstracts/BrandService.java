package com.kodlamaio.inventoryService.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryService.business.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateBrandResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllBrandResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateBrandResponse;


public interface BrandService {

	List<GetAllBrandResponse> getAll();
	CreateBrandResponse add(CreateBrandRequest createBrandRequest);
	void delete(String id);
	UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest);
	
	

}
