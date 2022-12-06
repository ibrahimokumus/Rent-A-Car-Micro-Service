package com.kodlamaoi.carFilterService.business.abstracts;

import java.util.List;

import com.kodlamaoi.carFilterService.business.response.GetAllCarFiltersResponse;

import com.kodlamaoi.carFilterService.entity.CarFilter;

public interface CarFilterService {
	
	
	void save(CarFilter carFilter);
    void delete(String id);
    List<GetAllCarFiltersResponse> getAll();
    List<GetAllCarFiltersResponse> getByBrandName(String brandName);
    List<GetAllCarFiltersResponse> getByModelName(String modelName);
    CarFilter getByCarId(String id);
    List<CarFilter> getByModelId(String modelId);
    List<CarFilter> getByBrandId(String brandId);
    void deleteByCarId(String id);
	

}
