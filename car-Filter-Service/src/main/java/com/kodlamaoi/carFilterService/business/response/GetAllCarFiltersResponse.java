package com.kodlamaoi.carFilterService.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarFiltersResponse {

	private String id;
	private String carId;
	private String brandId;
	private String brandName;
	private String modelId;
	private String modelName;
	private int modelYear;
	private double dailyPrice;
	private String plate;
	private int state;

}
