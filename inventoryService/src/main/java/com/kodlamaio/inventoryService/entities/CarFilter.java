package com.kodlamaio.inventoryService.entities;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CarFilter {
	
	@Id
	private String id;

	private String carId;

	private double carDailyPrice;

	private int carModelYear;

	private String carPlate;

	private String carModelId;

	private String carModelName;

	private String carModelBrandId;

	private String carModelBrandName;

}
