package com.kodlamaio.inventoryService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.inventoryService.business.abstracts.CarService;
import com.kodlamaoi.common.events.RentalCreatedEvent;
import com.kodlamaoi.common.events.RentalUpdatedCarEvent;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
	private CarService carService;

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "rentalCreate")
	public void consume(RentalCreatedEvent createdEvent) {
		LOGGER.info(String.format("Order event received in stock service => %s", createdEvent.toString()));
		carService.changeCarState(createdEvent.getCarId(), 2);
		LOGGER.info("Car rented");
		// save the order event into the database
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "rentalUpdate")
	public void consume(RentalUpdatedCarEvent RentalUpdatedCarEvent) {
		LOGGER.info(String.format("Order event received in stock service => %s", RentalUpdatedCarEvent.toString()));
		LOGGER.info("Car state changed");
		carService.changeCarState(RentalUpdatedCarEvent.getNewCarId(), 2);
		carService.changeCarState(RentalUpdatedCarEvent.getOldCarId(), 1);
		// save the order event into the database
	}

}
