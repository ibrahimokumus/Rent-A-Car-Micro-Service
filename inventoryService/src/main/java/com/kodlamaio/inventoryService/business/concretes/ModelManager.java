package com.kodlamaio.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.inventoryService.business.abstracts.ModelService;
import com.kodlamaio.inventoryService.business.request.create.CreateModelRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateModelResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllModelResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateModelResponse;
import com.kodlamaio.inventoryService.dataAccess.abstracts.ModelRepository;
import com.kodlamaio.inventoryService.entities.Model;
import com.kodlamaoi.common.utilities.exceptions.BusinessException;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	@Override
	public List<GetAllModelResponse> getAll() {
		List<Model> models = this.modelRepository.findAll();

		List<GetAllModelResponse> response = models.stream()
				.map(model -> this.modelMapperService.forResponse().map(model, GetAllModelResponse.class))
				.collect(Collectors.toList());

		return response;
		
	}

	@Override
	public CreateModelResponse add(CreateModelRequest createModelRequest) {
		//checkIfModelExistsByName(createModelRequest.getName());
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		
		this.modelRepository.save(model);
		
		CreateModelResponse createModelResponse = this.modelMapperService.forResponse().map(model, CreateModelResponse.class);
		return createModelResponse;
	}
	

	@Override
	public void delete(String id) {
		checkIfModelExistById(id);
		this.modelRepository.findById(id);
		
	}

	@Override
	public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
		checkIfModelExistById(updateModelRequest.getId());
		Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
        this.modelRepository.save(model);
        UpdateModelResponse response = this.modelMapperService.forResponse().map(model,
        		UpdateModelResponse.class);
        return response;
	}
	
//	private void checkIfModelExistsByName(String name) {
//		Model currentBrand = this.modelRepository.findByName(name);
//		if(currentBrand!=null) {
//		    throw new BusinessException("MODEL.EXISTS");
//		}
//	}
	private void checkIfModelExistById(String id) {
        if (modelRepository.findById(id) == null)
            throw new BusinessException("BRAND NOT FOUND");
    }

}
