package org.example.RentACar.utils.exception.Brand;

import org.example.RentACar.utils.exception.BusinessException;

public class BrandAlreadyExistsException extends BusinessException {
    public BrandAlreadyExistsException(String name){
        super("Brand " + name + " already exists.");
    }
}
