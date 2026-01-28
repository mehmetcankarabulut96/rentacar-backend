package org.example.RentACar.utils.mapper.Model;

import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Car.GetCarsByModelResponse;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import org.example.RentACar.entities.concretes.Brand;
import org.example.RentACar.entities.concretes.Model;

public class ModelMapperManager implements ModelMapperService{
    @Override
    public GetAllModelsResponse mapToGetAllModelsResponseFromModel(Model model) {
        GetAllModelsResponse response = new GetAllModelsResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setBrandName(model.getBrand().getName());

        return response;
    }

    @Override
    public Model mapToModelFromCreateModelRequest(CreateModelRequest createModelRequest) {
        Model model = new Model();
        model.setName(createModelRequest.getName());

        Brand brand = new Brand();
        brand.setId(createModelRequest.getBrandId());
        model.setBrand(brand);

        return model;
    }

    @Override
    public GetByIdModelResponse mapToGetByIdModelResponse(Model model) {
        GetByIdModelResponse response = new GetByIdModelResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setBrandName(model.getBrand().getName());
        response.setCars(model.getCars().stream().map(car -> new GetCarsByModelResponse(car.getId(), car.getPlate(), car.getDailyPrice(), car.getModelYear(), car.getState())).toList());

        return response;
    }

    @Override
    public void mapToExistingModel(UpdateModelRequest updateModelRequest, Model model) {
        model.setName(updateModelRequest.getName());

        Brand brand = new Brand();
        brand.setId(updateModelRequest.getBrandId());
        model.setBrand(brand);
    }
}