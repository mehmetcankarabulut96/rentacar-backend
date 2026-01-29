package org.example.RentACar.utils.mapper.Model;

import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import org.example.RentACar.entities.concretes.Model;

import java.util.List;

public interface ModelMapperService {
    GetAllModelsResponse mapToGetAllResponse(Model model);
    List<GetAllModelsResponse> mapToGetAllResponseList(List<Model> models);
    Model toModel(CreateModelRequest request);
    GetByIdModelResponse toGetByIdResponse(Model model);
    void updateModel(UpdateModelRequest request, Model model);
}