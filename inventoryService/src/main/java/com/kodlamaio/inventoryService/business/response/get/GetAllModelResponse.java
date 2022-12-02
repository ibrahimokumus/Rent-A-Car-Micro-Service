package com.kodlamaio.inventoryService.business.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetAllModelResponse {

	private String id;
    private String name;
    private String brandName;
}
