package com.kodlamaio.inventoryService.business.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetCarResponse {
	private String id;
	private double dailyPrice;
	private int modelYear;
	private String plate;
	private String brandName;
	private String modelName;
	private int state;

}
