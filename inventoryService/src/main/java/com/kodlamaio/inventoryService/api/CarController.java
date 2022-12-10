package com.kodlamaio.inventoryService.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.inventoryService.business.abstracts.CarService;
import com.kodlamaio.inventoryService.business.request.create.CreateCarRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateCarResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllCarResponse;
import com.kodlamaio.inventoryService.business.response.get.GetCarResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateCarResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {
	private CarService carService;
	
	@GetMapping
	public List<GetAllCarResponse> getAll(){
		return this.carService.getAll();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CreateCarResponse add(@RequestBody CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}
	@PutMapping("/{id}")
	public UpdateCarResponse update(@RequestBody UpdateCarRequest updateCarRequest,@PathVariable String id) {
		return this.carService.update(updateCarRequest,id);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		this.carService.delete(id);
	}
	@GetMapping("/{id}")
	public GetCarResponse getById(@PathVariable String id) {
		return this.carService.getById(id);
	}
	@GetMapping("/carAvialibleState/{carId}")
    public void carAvialibleState(@PathVariable String carId){
         this.carService.carAvialibleState(carId);
    }
}
