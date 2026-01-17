package org.example.RentACar.webApi.controllers;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.RentalService;
import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalsController {
    private RentalService rentalService;

    @PostMapping
    void add(@RequestBody CreateRentalRequest request){
        rentalService.add(request);
    }
}