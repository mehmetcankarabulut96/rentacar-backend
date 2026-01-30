package org.example.RentACar.webApi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.requests.Car.CreateCarRequest;
import org.example.RentACar.business.requests.Car.UpdateCarRequest;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.example.RentACar.business.responses.Car.GetByIdCarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
    private CarService carService;

    @GetMapping
    public PagedModel<EntityModel<GetAllCarsResponse>> getAll(
            @PageableDefault(size=4, sort="modelYear", direction = Sort.Direction.DESC) Pageable pageable,
            PagedResourcesAssembler<GetAllCarsResponse> assembler){
        Page<GetAllCarsResponse> carsPage = this.carService.getAll(pageable);

        return assembler.toModel(carsPage);
    }

    @GetMapping("/{id}")
    public GetByIdCarResponse getById(@PathVariable int id){
        return this.carService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid CreateCarRequest request){
        this.carService.add(request);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateCarRequest request){
        this.carService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.carService.delete(id);
    }
}