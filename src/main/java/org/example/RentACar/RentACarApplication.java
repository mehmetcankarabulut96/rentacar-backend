package org.example.RentACar;

import org.example.RentACar.utils.mapper.Brand.BrandMapperManager;
import org.example.RentACar.utils.mapper.Car.CarMapperManager;
import org.example.RentACar.utils.mapper.Model.ModelMapperManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentACarApplication {

	@Bean
	public CarMapperManager getCarMapperManager(){
		return new CarMapperManager();
	}

	@Bean
	public ModelMapperManager getModelMapperManager(){
		return new ModelMapperManager();
	}

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}
}
