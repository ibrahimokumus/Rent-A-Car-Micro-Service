package com.kodlamaio.inventoryService.business.request.update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateModelRequest {
	private String id;
    @NotBlank
    @NotNull
    @Size(min=2, max=20)
    private String name;
    @NotBlank
    @NotNull
    private String brandId;

}
