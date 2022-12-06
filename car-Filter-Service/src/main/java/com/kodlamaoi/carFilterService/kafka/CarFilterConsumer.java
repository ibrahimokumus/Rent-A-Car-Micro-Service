package com.kodlamaoi.carFilterService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.kodlamaoi.carFilterService.business.abstracts.CarFilterService;
import com.kodlamaoi.carFilterService.entity.CarFilter;
import com.kodlamaoi.common.events.CarCreatedEvent;
import com.kodlamaoi.common.events.CarUpdatedEvent;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;

public class CarFilterConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarFilterConsumer.class);
    private  CarFilterService carFilterService;
    private  ModelMapperService modelMapperService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "inventory-create"
    )
    public void consume(CarCreatedEvent event) {
        CarFilter carFilter = modelMapperService.forRequest().map(event, CarFilter.class);
        carFilterService.save(carFilter);
        LOGGER.info("Inventory created event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "inventory-update"
    )
    public void consume(CarUpdatedEvent event) {
        CarFilter filter = modelMapperService.forRequest().map(event, CarFilter.class);
        String id = carFilterService.getByCarId(event.getCarId()).getId();
        filter.setId(id);
        carFilterService.save(filter);
        LOGGER.info("Car updated event consumed: {}", event);
    }
}
