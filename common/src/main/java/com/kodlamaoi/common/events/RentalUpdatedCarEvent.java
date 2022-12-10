package com.kodlamaoi.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class RentalUpdatedCarEvent {

	private String message;
	private String oldCarId;
	private String newCarId;
	
	
	
	
	
	
	
	
}
