package org.example.RentACar.utils.exception.Brand;

import org.example.RentACar.utils.exception.BusinessException;

public class BrandNotFoundException extends BusinessException {
    public BrandNotFoundException(int id){
        super("Brand not found with id: " + id);
    }
}
