package com.kodlamaio.inventoryService.business.request.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBrandRequest {
	
	@NotBlank
    @NotNull
    @Size(min=2, max=20)
    private String name;

}