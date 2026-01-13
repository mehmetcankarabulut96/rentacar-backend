package org.example.RentACar.business.concretes;

import org.example.RentACar.business.abstracts.ModelService;
import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import org.example.RentACar.business.rules.BrandBusinessRules;
import org.example.RentACar.business.rules.ModelBusinessRules;
import org.example.RentACar.dataAccess.abstracts.BrandRepository;
import org.example.RentACar.dataAccess.abstracts.ModelRepository;
import org.example.RentACar.entities.concretes.Brand;
import org.example.RentACar.entities.concretes.Model;
import org.example.RentACar.utils.exception.Model.ModelNotFoundException;
import org.example.RentACar.utils.mapper.Model.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;
    private BrandRepository brandRepository;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = this.modelRepository.findAll();

        return models
                .stream()
                .map(modelMapperService::mapToGetAllModelsResponseFromModel)
                .toList();
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        int brandId = createModelRequest.getBrandId();

        brandBusinessRules.checkIfBrandNotExistsById(brandId);

        Brand brand = brandRepository.getReferenceById(brandId);

        Model model = modelMapperService.mapToModelFromCreateModelRequest(createModelRequest);
        model.setBrand(brand);

        this.modelRepository.save(model);
    }

    @Override
    public GetByIdModelResponse getById(int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));

        return modelMapperService.mapToGetByIdModelResponse(model);
    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        Model model = modelRepository.findById(updateModelRequest.getId())
                .orElseThrow(() -> new ModelNotFoundException(updateModelRequest.getId()));

        modelMapperService.mapToExistingModel(updateModelRequest, model);

        modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        modelBusinessRules.checkIfModelNotExists(id);

        modelRepository.deleteById(id);
    }
}