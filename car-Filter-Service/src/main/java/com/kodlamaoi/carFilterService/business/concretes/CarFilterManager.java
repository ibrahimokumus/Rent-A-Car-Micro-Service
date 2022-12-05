package com.kodlamaoi.carFilterService.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaoi.carFilterService.business.abstracts.CarFilterService;
import com.kodlamaoi.carFilterService.business.response.GetAllFilterResponse;
import com.kodlamaoi.carFilterService.dataAccess.CarFilterRepository;
import com.kodlamaoi.carFilterService.entity.CarFilter;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CarFilterManager implements CarFilterService {

	private CarFilterRepository repository;
	private ModelMapperService modelMapperService;
	@Override
	public List<GetAllFilterResponse> getAll() {
		List<CarFilter> filters = repository.findAll();
        List<GetAllFilterResponse> response = filters.stream()
                .map(filter -> modelMapperService.forResponse()
                		.map(filter, GetAllFilterResponse.class)).toList();
        return response;
	}
	@Override
	public List<GetAllFilterResponse> getByBrandName(String brandName) {
		List<CarFilter> filters = repository.findByBrandNameIgnoreCase(brandName);
        List<GetAllFilterResponse> response = filters.stream()
        		 .map(filter -> modelMapperService.forResponse()
                 		.map(filter, GetAllFilterResponse.class)).toList();
        return response;
	}
	@Override
	public List<GetAllFilterResponse> getByModelName(String modelName) {
		 List<CarFilter> filters = repository.findByModelNameIgnoreCase(modelName);
	        List<GetAllFilterResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllFilterResponse.class))
	                .toList();

	        return response;
	}
	@Override
	public List<GetAllFilterResponse> getByPlate(String plate) {
		  List<CarFilter> filters = repository.findByPlateIgnoreCase(plate);
		  List<GetAllFilterResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllFilterResponse.class))
	                .toList();

	        return response;
	}
	@Override
	public List<GetAllFilterResponse> searchByPlate(String plate) {
		 List<CarFilter> filters = repository.findByPlateContainingIgnoreCase(plate);
		 List<GetAllFilterResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllFilterResponse.class))
	                .toList();

	        return response;
	}
	@Override
	public List<GetAllFilterResponse> searchByBrandName(String brandName) {
		 List<CarFilter> filters = repository.findByBrandNameContainingIgnoreCase(brandName);
		 List<GetAllFilterResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllFilterResponse.class))
	                .toList();

	        return response;
		 
	}
	@Override
	public List<GetAllFilterResponse> searchByModelName(String modelName) {
		List<CarFilter> filters = repository.findByModelNameContainingIgnoreCase(modelName);
		 List<GetAllFilterResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllFilterResponse.class))
	                .toList();

	        return response;
	}
	@Override
	public List<GetAllFilterResponse> getByModelYear(int modelYear) {
		 List<CarFilter> filters = repository.findByModelYear(modelYear);
		 List<GetAllFilterResponse> response = filters
	                .stream()
	                .map(filter -> modelMapperService.forResponse().map(filter, GetAllFilterResponse.class))
	                .toList();

	        return response;
		 
		
	}
	@Override
	public List<GetAllFilterResponse> getByState(int state) {
		List<CarFilter> filters = repository.findByState(state);
		List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> modelMapperService.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
	}
	@Override
    public CarFilter getByCarId(String id) {
        return repository.findByCarId(id);
    }

    @Override
    public List<CarFilter> getByModelId(String modelId) {
        return repository.findByModelId(modelId);
    }

    @Override
    public List<CarFilter> getByBrandId(String brandId) {
        return repository.findByBrandId(brandId);
    }

    @Override
    public void save(CarFilter filter) {
        repository.save(filter);
    }

    @Override
    public void delete(String id) {
        repository.deleteByCarId(id);
    }

    @Override
    public void deleteAllByBrandId(String brandId) {
        repository.deleteAllByBrandId(brandId);
    }

    @Override
    public void deleteAllByModelId(String modelId) {
        repository.deleteAllByModelId(modelId);
    }

}
