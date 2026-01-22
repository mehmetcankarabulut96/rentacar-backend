package org.example.RentACar.business.abstracts;

import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import org.example.RentACar.entities.concretes.Model;

import java.util.List;

public interface ModelService {
    void add(CreateModelRequest createModelRequest);
    List<GetAllModelsResponse> getAll();
    GetByIdModelResponse getById(int id);
    void update(UpdateModelRequest updateModelRequest);
    void delete(int id);

    void checkIfModelExists(int id);
}