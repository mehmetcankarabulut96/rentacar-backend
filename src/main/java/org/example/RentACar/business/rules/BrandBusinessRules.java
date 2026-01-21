package org.example.RentACar.business.rules;

import org.example.RentACar.dataAccess.abstracts.BrandRepository;
import org.example.RentACar.dataAccess.abstracts.ModelRepository;
import org.example.RentACar.utils.exception.BusinessException;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository brandRepository;
    private ModelRepository modelRepository; // I have to inject this to break circular dependency

    public void checkIfBrandExistsByName(String name){
        if(this.brandRepository.existsByName(name)){
            throw new BusinessException("Brand " + name + " is already exists");
        }
    }

    public void checkIfBrandCanBeDeleted(int id){
        if(modelRepository.existsByBrandId(id)){
            throw new BusinessException("Brand can't be deleted because it has model(s)");
        }
    }

    public void checkIfBrandExistsById(int id){
        if(!brandRepository.existsById(id)){
            throw new BusinessException("Brand not found with id: " + id);
        }
    }
}