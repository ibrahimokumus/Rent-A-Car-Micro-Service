package com.kodlamaoi.rentalService.business.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalRequest {

	private String carId;
	private int rentedForDays;
	private LocalDateTime dateStarted;
	private double dailyPrice;
	private double totalPrice;
	
	
}
