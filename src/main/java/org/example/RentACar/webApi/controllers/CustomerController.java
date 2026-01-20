package org.example.RentACar.webApi.controllers;

import lombok.AllArgsConstructor;
import org.example.RentACar.business.abstracts.CustomerService;
import org.example.RentACar.business.requests.Customer.CreateCustomerRequest;
import org.example.RentACar.business.requests.Customer.UpdateCustomerRequest;
import org.example.RentACar.business.responses.Customer.GetAllCustomerResponse;
import org.example.RentACar.business.responses.Customer.GetByIdCustomerResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping
    public void add(@RequestBody CreateCustomerRequest request){
        customerService.add(request);
    }

    @GetMapping
    public List<GetAllCustomerResponse> getAll(){
        return this.customerService.getAllByDeletedFalse();
    }

    @GetMapping("/{id}")
    public GetByIdCustomerResponse getById(@PathVariable int id){
        return this.customerService.getById(id);
    }

    @PutMapping
    public void update(@RequestBody UpdateCustomerRequest request){
        this.customerService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.customerService.delete(id);
    }
}