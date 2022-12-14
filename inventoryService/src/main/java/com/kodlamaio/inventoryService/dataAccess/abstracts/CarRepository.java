package com.kodlamaio.inventoryService.dataAccess.abstracts;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.inventoryService.entities.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

	 boolean existsByPlateIgnoreCase(String plate);
	    
	    @Modifying
	    @Query(value = "update Cars set state = :state where id = :id", nativeQuery = true)
	    @Transactional
	    void setCarStateById(String id,int state);
	    Car findById(String id);
	    Car existsById(String id);
	    Car deleteById(String id);
}
