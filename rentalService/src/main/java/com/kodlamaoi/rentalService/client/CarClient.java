package com.kodlamaoi.rentalService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaio.inventoryService.business.response.get.GetCarResponse;

import feign.Headers;

@FeignClient(value = "carclient", url = "http://localhost:9010/")
public interface CarClient {

	@RequestMapping(method = RequestMethod.GET, value = "stock/api/cars/{id}")
	@Headers(value = "Content-Type: application/json")
	GetCarResponse checkIfCarAvailable(@PathVariable String id);
	
}