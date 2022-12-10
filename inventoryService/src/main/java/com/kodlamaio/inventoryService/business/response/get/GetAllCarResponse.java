package com.kodlamaio.inventoryService.business.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class GetAllCarResponse {

	private String id;
    private double dailyPrice;
    private int modelYear;
    private int state;
    private String modelId;
    private String modelBrandId;
    private String plate;
    private String brandName;
    private String modelName;
    private String modelBrandName;
	
}
