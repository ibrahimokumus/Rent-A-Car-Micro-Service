package com.kodlamaio.inventoryService.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryService.entities.Model;

public interface ModelRepository extends JpaRepository<Model, Integer>{

	Model findByName(String name);
	Model findById(String id);
}
