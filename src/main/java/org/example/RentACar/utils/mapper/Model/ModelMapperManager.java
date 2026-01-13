package org.example.RentACar.utils.mapper.Model;

import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
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

        return model;
    }

    @Override
    public GetByIdModelResponse mapToGetByIdModelResponse(Model model) {
        GetByIdModelResponse response = new GetByIdModelResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setBrandName(model.getBrand().getName());

        return response;
    }

    @Override
    public void mapToExistingModel(UpdateModelRequest updateModelRequest, Model model) {
        model.setName(updateModelRequest.getName());
    }
}
