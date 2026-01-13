package org.example.RentACar.utils.mapper.Brand;

import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;
import org.example.RentACar.entities.concretes.Brand;

public interface BrandMapperService {
    Brand mapToBrandFromCreateBrandRequest(CreateBrandRequest createBrandRequest);
    void mapToBrand(UpdateBrandRequest updateBrandRequest, Brand brand);
    GetAllBrandsResponse mapToGetAllBrandsResponseFromBrand(Brand brand);
    GetByIdBrandResponse mapToGetByIdBrandResponseFromBrand(Brand brand);
}
