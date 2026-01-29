package org.example.RentACar.utils.mapper.Brand;

import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;
import org.example.RentACar.business.responses.Model.GetModelsByBrandResponse;
import org.example.RentACar.entities.concretes.Brand;
import org.example.RentACar.entities.concretes.Model;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandMapperManager implements BrandMapperService{

    @Override
    public Brand toBrand(CreateBrandRequest createBrandRequest) {

        if(createBrandRequest == null) return null;

        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName());

        return brand;
    }

    @Override
    public GetAllBrandsResponse toGetAllResponse(Brand brand) {

        if(brand == null) return null;

        GetAllBrandsResponse response = new GetAllBrandsResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());

        return response;
    }

    @Override
    public List<GetAllBrandsResponse> toGetAllResponseList(List<Brand> brands) {

        if(brands == null) return List.of();

        return brands
                .stream()
                .map(this::toGetAllResponse)
                .toList();
    }

    @Override
    public GetByIdBrandResponse toGetByIdResponse(Brand brand) {

        if(brand == null) return null;

        GetByIdBrandResponse response = new GetByIdBrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setModels(toGetByBrandResponseList(brand.getModels()));

        return response;
    }

    @Override
    public void updateBrand(UpdateBrandRequest updateBrandRequest, Brand brand) {

        if(updateBrandRequest == null || brand == null) return;

        brand.setName(updateBrandRequest.getName());
    }

    private GetModelsByBrandResponse toGetByBrandResponse(Model model){

        if(model == null) return null;

        GetModelsByBrandResponse response = new GetModelsByBrandResponse();
        response.setId(model.getId());
        response.setName(model.getName());

        return response;
    }

    private List<GetModelsByBrandResponse> toGetByBrandResponseList(List<Model> models){

        if(models == null) return List.of();

        return models
                .stream()
                .map(this::toGetByBrandResponse)
                .toList();
    }
}