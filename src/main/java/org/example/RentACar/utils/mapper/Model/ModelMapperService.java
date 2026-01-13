package org.example.RentACar.utils.mapper.Model;

import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import org.example.RentACar.entities.concretes.Model;

public interface ModelMapperService {
    GetAllModelsResponse mapToGetAllModelsResponseFromModel(Model model);
    Model mapToModelFromCreateModelRequest(CreateModelRequest createModelRequest);
    GetByIdModelResponse mapToGetByIdModelResponse(Model model);
    void mapToExistingModel(UpdateModelRequest updateModelRequest, Model model);
}
