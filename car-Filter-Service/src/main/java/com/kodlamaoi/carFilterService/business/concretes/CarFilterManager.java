package com.kodlamaoi.carFilterService.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaoi.carFilterService.business.abstracts.CarFilterService;
import com.kodlamaoi.carFilterService.business.response.GetAllCarFiltersResponse;

import com.kodlamaoi.carFilterService.dataAccess.CarFilterRepository;
import com.kodlamaoi.carFilterService.entity.CarFilter;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CarFilterManager implements CarFilterService {

	 private CarFilterRepository carFilterRepository;
	    private ModelMapperService modelMapperService;

	    @Override
	    public void save(CarFilter carFilter) {
	        carFilterRepository.save(carFilter);
	    }

	    @Override
	    public void delete(String id) {
	        carFilterRepository.deleteById(id);
	    }

	    @Override
	    public List<GetAllCarFiltersResponse> getAll() {
	        List<CarFilter> cars = carFilterRepository.findAll();
	        List<GetAllCarFiltersResponse> response = cars
	                .stream().map(carFilter -> modelMapperService.forResponse().map(carFilter,GetAllCarFiltersResponse.class))
	                .collect(Collectors.toList());
	        return response;
	    }

	    @Override
	    public List<GetAllCarFiltersResponse> getByBrandName(String brandName) {
	        List<CarFilter> filters = carFilterRepository.findByBrandNameContainingIgnoreCase(brandName);
	        List<GetAllCarFiltersResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllCarFiltersResponse.class))
	                .collect(Collectors.toList());
	        return response;
	    }

	    @Override
	    public List<GetAllCarFiltersResponse> getByModelName(String modelName) {
	        List<CarFilter> filters = carFilterRepository.findByModelNameContainingIgnoreCase(modelName);
	        List<GetAllCarFiltersResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllCarFiltersResponse.class))
	                .collect(Collectors.toList());
	        return response;
	    }

	    @Override
	    public CarFilter getByCarId(String id) {
	        return carFilterRepository.findByCarId(id);
	    }

	    @Override
	    public List<CarFilter> getByModelId(String modelId) {
	        return carFilterRepository.findAllByModelId(modelId);
	    }

	    @Override
	    public List<CarFilter> getByBrandId(String brandId) {
	        return carFilterRepository.findAllByModelId(brandId);
	    }

	    @Override
	    public void deleteByCarId(String id) {
	        carFilterRepository.deleteByCarId(id);
	    }
}
