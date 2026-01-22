package org.example.RentACar.business.rules;

import org.example.RentACar.business.abstracts.BrandService;
import org.example.RentACar.dataAccess.abstracts.CarRepository;
import org.example.RentACar.dataAccess.abstracts.ModelRepository;
import org.example.RentACar.utils.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private ModelRepository modelRepository;

    private BrandService brandService;
    private CarRepository carRepository; // call repository instead of service to prevent circular dependency between Model <-> Car

    public void checkIfModelExistsById(int id){
        if(!modelRepository.existsById(id)){
            throw new BusinessException("Model not found with id: " + id);
        }
    }

    public void checkIfModelExistsByName(String name){
        if(modelRepository.existsByName(name)){
            throw new BusinessException("Model " + name + " is already exists");
        }
    }

    public void checkIfModelLimitExceededForBrand(int brandId){
        if(modelRepository.countByBrandId(brandId) >= 5){
            throw new BusinessException("Maximum model limit reached for the brand with id: " + brandId);
        }
    }

    public void checkIfModelNameExistsForUpdate(int id, String newName){
        if(modelRepository.existsByNameIgnoreCaseAndIdNot(newName, id)){
            throw new BusinessException("Model " + newName + " is already exists");
        }
    }

    public void checkIfBrandCanBeChanged(int newBrandId, int currentBrandId){
        if(newBrandId == currentBrandId){
            return;
        }

        brandService.checkIfBrandExists(newBrandId);
    }

    public void checkIfModelCanBeDeleted(int id){
        if(carRepository.existsByModelId(id)){
            throw new BusinessException("Model can not be deleted because it has car(s)");
        }
    }
}