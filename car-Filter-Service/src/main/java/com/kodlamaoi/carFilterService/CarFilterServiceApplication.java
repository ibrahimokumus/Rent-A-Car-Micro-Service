package com.kodlamaoi.carFilterService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.kodlamaoi.common.utilities.mapping.ModelMapperManager;
import com.kodlamaoi.common.utilities.mapping.ModelMapperService;

@SpringBootApplication
@EnableDiscoveryClient
public class CarFilterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarFilterServiceApplication.class, args);
	}
	
	@Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapperService getModelMapperService(ModelMapper mapper) {
        return new ModelMapperManager(mapper);
    }

   
    
    
}
