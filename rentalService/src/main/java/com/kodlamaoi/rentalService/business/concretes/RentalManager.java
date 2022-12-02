package com.kodlamaoi.rentalService.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.inventoryService.business.response.get.GetCarResponse;
import com.kodlamaoi.common.events.RentalCreatedEvent;
import com.kodlamaoi.common.events.RentalUpdatedEvent;
import com.kodlamaoi.common.utilities.exceptions.BusinessException;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;
import com.kodlamaoi.rentalService.business.abstracts.RentalService;
import com.kodlamaoi.rentalService.business.request.CreateRentalRequest;
import com.kodlamaoi.rentalService.business.request.UpdateRentalRequest;
import com.kodlamaoi.rentalService.business.response.CreateRentalResponse;
import com.kodlamaoi.rentalService.business.response.GetAllRentalResponse;
import com.kodlamaoi.rentalService.business.response.UpdateRentalResponse;
import com.kodlamaoi.rentalService.client.CarClient;
import com.kodlamaoi.rentalService.dataAccess.abstracts.RentalRepository;
import com.kodlamaoi.rentalService.entity.Rental;
import com.kodlamaoi.rentalService.kafka.RentalProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class RentalManager implements RentalService {

	private ModelMapperService modelMapperService;
	private RentalRepository rentalRepository;
	private RentalProducer rentalProducer;
	private CarClient carClient;

	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
			checkIfRentalExistById(createRentalRequest.getCarId());
			checkIfCarAvailable(createRentalRequest.getCarId());
	        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
	        rental.setId(UUID.randomUUID().toString());
	        rental.setDateStarted(LocalDateTime.now());
	        Rental rentalCreated = rentalRepository.save(rental);

	        RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
	        rentalCreatedEvent.setCarId(rentalCreated.getCarId());
	        rentalCreatedEvent.setMessage("Rental Created");

	        this.rentalProducer.sendMessage(rentalCreatedEvent);

	        CreateRentalResponse response = modelMapperService.forResponse().map(rental, CreateRentalResponse.class);

	        return response;
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest, String id) {
		checkIfRentalNotExistById(updateRentalRequest.getCarId());
		RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();
		Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setId(id);
		rentalUpdatedEvent.setOldCarId(rentalRepository.findById(id).getCarId());
		rentalRepository.save(rental);

		rentalUpdatedEvent.setNewCarId(rental.getCarId());
		rentalUpdatedEvent.setMessage("Rental Updated");

		this.rentalProducer.sendMessage(rentalUpdatedEvent);

		UpdateRentalResponse data = modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);

		return data;
	}

	@Override
	public List<GetAllRentalResponse> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public void delete(String id) {
		checkIfRentalNotExistById(id);
		this.rentalRepository.findById(id);

	}

	private void checkIfRentalExistById(String id) {

		Rental rental = this.rentalRepository.findById(id);
		if (rental != null) {
			throw new BusinessException("Rental Exists!");
		}
	}

	private void checkIfRentalNotExistById(String id) {

		Rental rental = this.rentalRepository.findById(id);
		if (rental == null) {
			throw new BusinessException("Rental Not Found!");
		}
	}

	private void checkIfCarAvailable(String carId) {
		GetCarResponse response = carClient.checkIfCarAvailable(carId);
		if (response.getState() != 1)
			throw new BusinessException("The Car NOT AVAILABLE");
	}
}
