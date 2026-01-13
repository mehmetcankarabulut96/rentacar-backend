package org.example.RentACar.utils.exception.Model;

import org.example.RentACar.utils.exception.BusinessException;

public class ModelNotFoundException extends BusinessException {
    public ModelNotFoundException(int id) {
        super("Model not found with id: " + id);
    }
}
