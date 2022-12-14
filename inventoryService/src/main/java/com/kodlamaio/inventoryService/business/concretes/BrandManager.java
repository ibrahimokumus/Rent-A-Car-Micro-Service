package com.kodlamaio.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.inventoryService.business.abstracts.BrandService;
import com.kodlamaio.inventoryService.business.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryService.business.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryService.business.response.create.CreateBrandResponse;
import com.kodlamaio.inventoryService.business.response.get.GetAllBrandResponse;
import com.kodlamaio.inventoryService.business.response.update.UpdateBrandResponse;
import com.kodlamaio.inventoryService.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.inventoryService.entities.Brand;
import com.kodlamaoi.common.utilities.exceptions.BusinessException;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	@Override
	public List<GetAllBrandResponse> getAll() {
		List<Brand> brands = this.brandRepository.findAll();

		List<GetAllBrandResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@Override
	public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
		//checkIfBrandExistsByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());
		
		this.brandRepository.save(brand);
		
		CreateBrandResponse createBrandResponse = this.modelMapperService.forResponse().map(brand, CreateBrandResponse.class);
		return createBrandResponse;
	}

	

	@Override
	public void delete(String id) {
		checkIfBrandExistById(id);
		this.brandRepository.findById(id);
		
	}

	@Override
	public UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest) {
		checkIfBrandExistById(updateBrandRequest.getId());
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandRepository.save(brand);
        UpdateBrandResponse response = this.modelMapperService.forResponse().map(brand,
                UpdateBrandResponse.class);
        return response;
	}
//	private void checkIfBrandExistsByName(String name) {
//		Brand currentBrand = this.brandRepository.findByName(name);
//		if(currentBrand!=null) {
//		    throw new BusinessException("BRAND.EXISTS");
//		}
//	}
	private void checkIfBrandExistById(String id) {
        if (brandRepository.findById(id) == null)
            throw new BusinessException("BRAND NOT FOUND");
    }
}
