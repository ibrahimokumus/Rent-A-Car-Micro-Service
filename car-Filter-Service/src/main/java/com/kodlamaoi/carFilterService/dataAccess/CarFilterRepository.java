package com.kodlamaoi.carFilterService.dataAccess;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaoi.carFilterService.entity.CarFilter;

public interface CarFilterRepository extends MongoRepository<CarFilter, String> {

	List<CarFilter> findByBrandNameContainingIgnoreCase(String brandName);
    List<CarFilter> findByModelNameContainingIgnoreCase(String brandName);
    CarFilter findByCarId(String carId);
    CarFilter findByModelId(String modelId);
    List<CarFilter> findAllByModelId(String modelId);
    List<CarFilter> findAllByBrandId(String modelId);
    void deleteByCarId(String carId);

}
