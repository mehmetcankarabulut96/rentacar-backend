package org.example.RentACar.business.concretes;

import org.example.RentACar.business.abstracts.BrandService;
import org.example.RentACar.business.abstracts.ModelService;
import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;
import org.example.RentACar.business.rules.BrandBusinessRules;
import org.example.RentACar.dataAccess.abstracts.BrandRepository;
import org.example.RentACar.entities.concretes.Brand;
import org.example.RentACar.utils.exception.Brand.BrandNotFoundException;
import org.example.RentACar.utils.exception.BusinessException;
import org.example.RentACar.utils.mapper.Brand.BrandMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapperService brandMapperService;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public void add(CreateBrandRequest createBrandRequest){
        String name = createBrandRequest.getName();

        brandBusinessRules.checkIfBrandExistsByName(name);

        Brand brand = brandMapperService.toBrand(createBrandRequest);

        brandRepository.save(brand);
    }

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();

        return brandMapperService.toGetAllResponseList(brands);
    }

    @Override
    @Transactional(readOnly = true)
    public GetByIdBrandResponse getById(int id) {
        // ( spring.jpa.open-in-view: false ) && ( not @Transactional ) => LazyInitializationException
        // Brand brand = brandRepository.getReferenceById(id);

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));

        return brandMapperService.toGetByIdResponse(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        int id = updateBrandRequest.getId();

        Brand brand = brandRepository.findById(id)
                        .orElseThrow(() -> new BrandNotFoundException(id));

        brandMapperService.updateBrand(updateBrandRequest, brand);

        brandRepository.save(brand);
    }

    @Override
    public void delete(int id){
        brandBusinessRules.checkIfBrandExistsById(id);

        brandBusinessRules.checkIfBrandCanBeDeleted(id);

        brandRepository.deleteById(id);
    }

    @Override
    public void checkIfBrandExists(int id){
        brandBusinessRules.checkIfBrandExistsById(id);
    }
}