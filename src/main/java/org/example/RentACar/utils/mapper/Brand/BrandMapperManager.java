package org.example.RentACar.utils.mapper.Brand;

import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;
import org.example.RentACar.entities.concretes.Brand;

public class BrandMapperManager implements BrandMapperService{
    @Override
    public Brand mapToBrandFromCreateBrandRequest(CreateBrandRequest createBrandRequest) {
        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName());

        return brand;
    }

    @Override
    public void mapToBrand(UpdateBrandRequest updateBrandRequest, Brand brand) {
        brand.setName(updateBrandRequest.getName());
    }

    @Override
    public GetAllBrandsResponse mapToGetAllBrandsResponseFromBrand(Brand brand) {
        GetAllBrandsResponse response = new GetAllBrandsResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());

        return response;
    }

    @Override
    public GetByIdBrandResponse mapToGetByIdBrandResponseFromBrand(Brand brand) {
        GetByIdBrandResponse response = new GetByIdBrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());

        return response;
    }
}
