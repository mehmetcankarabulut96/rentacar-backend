package org.example.RentACar.business.rules;

import org.example.RentACar.dataAccess.abstracts.BrandRepository;
import org.example.RentACar.dataAccess.abstracts.ModelRepository;
import org.example.RentACar.utils.exception.Brand.BrandAlreadyExistsException;
import org.example.RentACar.utils.exception.Brand.BrandNotFoundException;
import org.example.RentACar.utils.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public void checkIfBrandExistsByName(String name){
        if(this.brandRepository.existsByName(name)){
            throw new BrandAlreadyExistsException(name);
        }
    }

    public void checkIfBrandNotExistsById(int id){
        if(!brandRepository.existsById(id)){
            throw new BrandNotFoundException(id);
        }
    }

    public void checkIfBrandHasModels(int id){
        if(modelRepository.existsByBrandId(id)){
            throw new BusinessException("Brand cannot be deleted because it has related models.");
        }
    }
}