package org.example.RentACar.business.concretes;

import org.example.RentACar.business.abstracts.BrandService;
import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;
import org.example.RentACar.business.rules.BrandBusinessRules;
import org.example.RentACar.dataAccess.abstracts.BrandRepository;
import org.example.RentACar.entities.concretes.Brand;
import org.example.RentACar.utils.exception.Brand.BrandNotFoundException;
import org.example.RentACar.utils.mapper.Brand.BrandMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapperService brandMapperService;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public void add(CreateBrandRequest createBrandRequest){
        this.brandBusinessRules.checkIfBrandExistsByName(createBrandRequest.getName());

        Brand brand = brandMapperService.mapToBrandFromCreateBrandRequest(createBrandRequest);

        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = brandRepository.findById(updateBrandRequest.getId())
                        .orElseThrow(() -> new BrandNotFoundException(updateBrandRequest.getId()));

        brandMapperService.mapToBrand(updateBrandRequest, brand);

        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id){
        brandBusinessRules.checkIfBrandNotExistsById(id);

        brandBusinessRules.checkIfBrandHasModels(id);

        this.brandRepository.deleteById(id);
    }

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = this.brandRepository.findAll();

        return brands
                .stream()
                .map(brandMapperService::mapToGetAllBrandsResponseFromBrand)
                .toList();
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        // ( spring.jpa.open-in-view: false ) && ( not @Transactional ) => LazyInitializationException
        // Brand brand = brandRepository.getReferenceById(id);

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));

        return brandMapperService.mapToGetByIdBrandResponseFromBrand(brand);
    }
}