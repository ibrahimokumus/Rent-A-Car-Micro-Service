package com.kodlamaoi.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarUpdatedEvent {
	 	private String carId;
	    private String modelId;
	    private String brandId;
	    private String modelName;
	    private String brandName;
	    private double dailyPrice;
	    private int modelYear;
	    private String plate;
	    private int state;

}
