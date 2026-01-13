package org.example.RentACar.business.abstracts;

import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetByIdBrandResponse getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);
}
