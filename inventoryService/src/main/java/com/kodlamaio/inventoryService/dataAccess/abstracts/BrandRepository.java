package com.kodlamaio.inventoryService.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryService.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

	Brand findByName(String name);
	Brand findById(String id);
}
