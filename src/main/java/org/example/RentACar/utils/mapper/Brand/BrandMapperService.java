package org.example.RentACar.utils.mapper.Brand;

import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;
import org.example.RentACar.entities.concretes.Brand;

import java.util.List;

public interface BrandMapperService {
    Brand toBrand(CreateBrandRequest createBrandRequest);
    void updateBrand(UpdateBrandRequest updateBrandRequest, Brand brand);
    GetAllBrandsResponse toGetAllResponse(Brand brand);
    List<GetAllBrandsResponse> toGetAllResponseList(List<Brand> brands);
    GetByIdBrandResponse toGetByIdResponse(Brand brand);
}