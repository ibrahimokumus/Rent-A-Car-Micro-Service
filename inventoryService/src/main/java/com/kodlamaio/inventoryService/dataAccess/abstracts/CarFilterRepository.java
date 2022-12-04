package com.kodlamaio.inventoryService.dataAccess.abstracts;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.inventoryService.entities.CarFilter;

public interface CarFilterRepository  extends MongoRepository<CarFilter, String>{
	
	
	

}
