package com.kodlamaio.inventoryService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.inventoryService.business.abstracts.CarService;
import com.kodlamaoi.common.events.RentalCreatedEvent;
import com.kodlamaoi.common.events.RentalUpdatedEvent;

@Service
public class RentalConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
	CarService carService;
    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(RentalCreatedEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

       carService.changeCarState(event.getCarId());
       LOGGER.info(event.getCarId()+"state change");
        
        // save the order event into the database
    }
    
    public void consume(RentalUpdatedEvent event) {
    	LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
    	
    	carService.changeCarState(event.getOldCarId()+"change to"+ event.getNewCarId());
    	LOGGER.info(event.getOldCarId()+" change to "+event.getNewCarId());
    	
    }
    
    
}
