package com.kodlamaio.inventoryService.business.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateBrandRequest {

	private String id;
	private String name;
	
}
