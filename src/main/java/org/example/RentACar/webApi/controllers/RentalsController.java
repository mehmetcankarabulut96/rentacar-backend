package org.example.RentACar.webApi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.RentalService;
import org.example.RentACar.business.requests.Rental.CreateRentalRequest;
import org.example.RentACar.business.requests.Rental.UpdateRentalRequest;
import org.example.RentACar.business.responses.Rental.GetAllRentalResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalsController {
    private RentalService rentalService;

    @PostMapping
    public void add(@RequestBody @Valid CreateRentalRequest request){
        this.rentalService.add(request);
    }

    @GetMapping
    public List<GetAllRentalResponse> getAll(){
        return this.rentalService.getAll();
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateRentalRequest request){
        this.rentalService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.rentalService.delete(id);
    }
}