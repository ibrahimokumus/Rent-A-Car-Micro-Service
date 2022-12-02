package com.kodlamaoi.rentalService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaoi.common.events.RentalCreatedEvent;
import com.kodlamaoi.common.events.RentalUpdatedEvent;

@Service
public class RentalProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalProducer.class);

	private NewTopic topic;
	
	private KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate;
	private KafkaTemplate<String, RentalUpdatedEvent> kafkaTemplate2;

	public RentalProducer(NewTopic topic, KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
		LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));
		
		Message<RentalCreatedEvent> message = MessageBuilder
				.withPayload(rentalCreatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaTemplate.send(message);
	}
	
	public void sendMessage(RentalUpdatedEvent rentalUpdatedEvent) {
		LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString()));
		
		Message<RentalUpdatedEvent> message = MessageBuilder
				.withPayload(rentalUpdatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaTemplate2.send(message);
	}
	
	
	
	
}