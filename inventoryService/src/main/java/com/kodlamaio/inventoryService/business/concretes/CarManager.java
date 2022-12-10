package com.kodlamaio.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.inventoryService.business.abstracts.CarService;
import com.kodlamaio.inventoryService.business.request.create.CreateCarRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateCarResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllCarResponse;
import com.kodlamaio.inventoryService.business.response.get.GetCarResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateCarResponse;
import com.kodlamaio.inventoryService.dataAccess.abstracts.CarRepository;
import com.kodlamaio.inventoryService.entities.Car;
import com.kodlamaio.inventoryService.kafka.FilterServiceProducer;
import com.kodlamaoi.common.events.CarCreatedEvent;
import com.kodlamaoi.common.events.CarUpdatedEvent;
import com.kodlamaoi.common.utilities.exceptions.BusinessException;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CarManager implements CarService {
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private FilterServiceProducer filterServiceProducer;

	// 1->available
	// 2->notAvailable
	// 3->maintenance


    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> response = cars
                .stream()
                .map(car -> modelMapperService.forResponse().map(car, GetAllCarResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(String id) {
        checkIfCarExistsById(id);
        Car car = carRepository.findById(id);
        GetCarResponse response = modelMapperService.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        checkIfCarExistsByPlate(request.getPlate());
        Car car = modelMapperService.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID().toString());
        carRepository.save(car);
        CreateCarResponse response = modelMapperService.forResponse().map(car, CreateCarResponse.class);
        addToFilterService(response.getId());
        return response;
    }

    @Override
    public UpdateCarResponse update(UpdateCarRequest updateCarRequest, String id) {
        checkIfCarExistsById(id);
        checkIfCarExistsByPlate(updateCarRequest.getPlate());
        Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
        car.setId(id);
        carRepository.save(car);
        UpdateCarResponse response = modelMapperService.forResponse().map(car, UpdateCarResponse.class);
        updateToFilterService(updateCarRequest,id);
        return response;
    }

    @Override
    public void delete(String id) {
        checkIfCarExistsById(id);
        carRepository.deleteById(id);
    }

    @Override
    public void changeCarState(String id, int state) {
        carRepository.setCarStateById(id,state);
    }

    @Override
    public void carAvialibleState(String carId) {
        checkIfCarAvialible(carId);
    }

    
    
    //is kodlari
    
    
    private void checkIfCarExistsById(String id) {
        if (carRepository.existsById(id)!=null) {
            throw new BusinessException("CAR.NOT_EXISTS");
        }
    }

    private void checkIfCarExistsByPlate(String plate) {
        if (carRepository.existsByPlateIgnoreCase(plate)) {
            throw new BusinessException("CAR.ALREADY_EXISTS");
        }
    }

    private void checkIfCarAvialible(String carId){
        Car car = carRepository.findById(carId);
        if(car == null || car.getState() != 1)
            throw new BusinessException("Car no exists or car no avialible");
    }

    private void addToFilterService(String id) {
        Car car = carRepository.findById(id);
        CarCreatedEvent event = modelMapperService.forResponse().map(car,CarCreatedEvent.class);
        event.setMessage("car added");
        filterServiceProducer.sendMessage(event);
    }

    private void updateToFilterService(UpdateCarRequest updateCarRequest, String id) {
        Car car = carRepository.findById(id);
        car.getModel().setId(updateCarRequest.getModelId());
        car.getModel().getBrand().setId(car.getModel().getBrand().getId());
        car.setState(updateCarRequest.getState());
        car.setPlate(updateCarRequest.getPlate());
        car.setModelYear(updateCarRequest.getModelYear());
        car.setDailyPrice(updateCarRequest.getDailyPrice());
        CarUpdatedEvent event = modelMapperService.forResponse().map(car,CarUpdatedEvent.class);
        filterServiceProducer.sendMessage(event);
    }
	

}
