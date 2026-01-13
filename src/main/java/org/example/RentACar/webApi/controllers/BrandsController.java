package org.example.RentACar.webApi.controllers;

import jakarta.validation.Valid;
import org.example.RentACar.business.abstracts.BrandService;
import org.example.RentACar.business.requests.Brand.CreateBrandRequest;
import org.example.RentACar.business.requests.Brand.UpdateBrandRequest;
import org.example.RentACar.business.responses.Brand.GetAllBrandsResponse;
import org.example.RentACar.business.responses.Brand.GetByIdBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private final BrandService brandService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.brandService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest){
        this.brandService.update(updateBrandRequest);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid CreateBrandRequest createBrandRequest){
        this.brandService.add(createBrandRequest);
    }

    @GetMapping
    public List<GetAllBrandsResponse> getAll(){
        return this.brandService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdBrandResponse getById(@PathVariable int id){
        return brandService.getById(id);
    }
}