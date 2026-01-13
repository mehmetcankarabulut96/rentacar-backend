package org.example.RentACar.webApi.controllers;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CarService;
import org.example.RentACar.business.responses.Car.GetAllCarsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
    private CarService carService;

    @GetMapping
    public Page<GetAllCarsResponse> getAll(
            @PageableDefault(size=4, sort="modelYear", direction = Sort.Direction.DESC) Pageable pageable){
        return carService.getAll(pageable);
    }
}