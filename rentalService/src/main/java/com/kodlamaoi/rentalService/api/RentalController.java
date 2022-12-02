package com.kodlamaoi.rentalService.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaoi.rentalService.business.abstracts.RentalService;
import com.kodlamaoi.rentalService.business.request.CreateRentalRequest;
import com.kodlamaoi.rentalService.business.request.UpdateRentalRequest;
import com.kodlamaoi.rentalService.business.response.CreateRentalResponse;
import com.kodlamaoi.rentalService.business.response.GetAllRentalResponse;
import com.kodlamaoi.rentalService.business.response.UpdateRentalResponse;

import lombok.AllArgsConstructor;

@RequestMapping("/api/rentals")
@AllArgsConstructor
@RestController
public class RentalController {
	
	private RentalService rentalService;
	
	
	
	@GetMapping
	public List<GetAllRentalResponse> getAll(){
		return this.rentalService.getAll();
	}
	
	@PostMapping
	
	public CreateRentalResponse add(@RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
	}
	
	@PutMapping
	public UpdateRentalResponse update(@RequestBody UpdateRentalRequest updateRentalRequest,String id) {
		return this.rentalService.update(updateRentalRequest,id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		this.rentalService.delete(id);
	}
	
	

}
