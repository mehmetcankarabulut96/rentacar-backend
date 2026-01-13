package org.example.RentACar.business.rules;

import org.example.RentACar.dataAccess.abstracts.ModelRepository;
import org.example.RentACar.utils.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private ModelRepository modelRepository;

    public void checkIfModelNotExists(int id){
        if(!modelRepository.existsById(id)){
            throw new BusinessException("Model not found with id: " + id);
        }
    }
}