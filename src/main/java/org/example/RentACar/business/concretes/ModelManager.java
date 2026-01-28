package org.example.RentACar.business.concretes;

import org.example.RentACar.business.abstracts.BrandService;
import org.example.RentACar.business.abstracts.ModelService;
import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import org.example.RentACar.business.rules.ModelBusinessRules;
import org.example.RentACar.dataAccess.abstracts.ModelRepository;
import org.example.RentACar.entities.concretes.Model;
import org.example.RentACar.utils.exception.Model.ModelNotFoundException;
import org.example.RentACar.utils.mapper.Model.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;

    private BrandService brandService;

    @Override
    public void add(CreateModelRequest createModelRequest) {
        String name = createModelRequest.getName();
        int brandId = createModelRequest.getBrandId();

        modelBusinessRules.checkIfModelExistsByName(name);

        brandService.checkIfBrandExists(brandId);

        modelBusinessRules.checkIfModelLimitExceededForBrand(brandId);

        Model model = modelMapperService.mapToModelFromCreateModelRequest(createModelRequest);

        modelRepository.save(model);
    }

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = this.modelRepository.findAll();

        return models
                .stream()
                .map(modelMapperService::mapToGetAllModelsResponseFromModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GetByIdModelResponse getById(int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));

        return modelMapperService.mapToGetByIdModelResponse(model);
    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        int id = updateModelRequest.getId();

        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));

        modelBusinessRules.checkIfModelNameExistsForUpdate(id, updateModelRequest.getName());

        modelBusinessRules.checkIfBrandCanBeChanged(updateModelRequest.getBrandId(), model.getBrand().getId());

        modelMapperService.mapToExistingModel(updateModelRequest, model);

        modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        modelBusinessRules.checkIfModelExistsById(id);

        modelBusinessRules.checkIfModelCanBeDeleted(id);

        modelRepository.deleteById(id);
    }

    @Override
    public void checkIfModelExists(int id) {
        modelBusinessRules.checkIfModelExistsById(id);
    }
}