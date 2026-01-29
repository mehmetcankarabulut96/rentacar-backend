package org.example.RentACar.utils.mapper.Model;

import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Car.GetCarsByModelResponse;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import org.example.RentACar.entities.concretes.Brand;
import org.example.RentACar.entities.concretes.Car;
import org.example.RentACar.entities.concretes.Model;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelMapperManager implements ModelMapperService{

    private String getBrandName(Brand brand){
        return (brand != null)? brand.getName(): null;
    }

    @Override
    public GetAllModelsResponse mapToGetAllResponse(Model model) {

        if(model == null) return null;

        GetAllModelsResponse response = new GetAllModelsResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setBrandName(getBrandName(model.getBrand()));

        return response;
    }

    @Override
    public List<GetAllModelsResponse> mapToGetAllResponseList(List<Model> models) {

        if(models == null) return List.of();

        return models
                .stream()
                .map(this::mapToGetAllResponse)
                .toList();
    }

    private Brand toBrand(Integer brandId){

        if(brandId == null) return null;

        Brand brand = new Brand();
        brand.setId(brandId);

        return brand;
    }

    @Override
    public Model toModel(CreateModelRequest request) {

        if(request == null) return null;

        Model model = new Model();
        model.setName(request.getName());
        model.setBrand(toBrand(request.getBrandId()));

        return model;
    }

    private GetCarsByModelResponse toGetCarsByModel(Car car){

        if(car == null) return null;

        GetCarsByModelResponse response = new GetCarsByModelResponse();
        response.setId(car.getId());
        response.setPlate(car.getPlate());
        response.setDailyPrice(car.getDailyPrice());
        response.setModelYear(car.getModelYear());
        response.setState(car.getState());

        return response;
    }

    private List<GetCarsByModelResponse> toGetCarsByModelList(List<Car> cars){

        if(cars == null) return List.of();

        return cars
                .stream()
                .map(this::toGetCarsByModel)
                .toList();
    }

    @Override
    public GetByIdModelResponse toGetByIdResponse(Model model) {

        if(model == null) return null;

        GetByIdModelResponse response = new GetByIdModelResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setBrandName(getBrandName(model.getBrand()));
        response.setCars(toGetCarsByModelList(model.getCars()));

        return response;
    }

    @Override
    public void updateModel(UpdateModelRequest request, Model model) {

        if(request == null || model == null) return;

        model.setName(request.getName());
        model.setBrand(toBrand(request.getBrandId()));
    }
}