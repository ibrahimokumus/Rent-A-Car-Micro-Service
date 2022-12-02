package com.kodlamaio.inventoryService.business.response.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelResponse {
	private String id;
	private String name;
	private String brandId;

}