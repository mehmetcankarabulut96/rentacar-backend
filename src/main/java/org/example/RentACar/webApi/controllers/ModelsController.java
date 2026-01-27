package org.example.RentACar.webApi.controllers;

import jakarta.validation.Valid;
import org.example.RentACar.business.abstracts.ModelService;
import org.example.RentACar.business.requests.Model.CreateModelRequest;
import org.example.RentACar.business.requests.Model.UpdateModelRequest;
import org.example.RentACar.business.responses.Model.GetAllModelsResponse;
import org.example.RentACar.business.responses.Model.GetByIdModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @GetMapping
    public List<GetAllModelsResponse> getAll(){
        return this.modelService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid CreateModelRequest createModelRequest){
        this.modelService.add(createModelRequest);
    }

    @GetMapping("/{id}")
    public GetByIdModelResponse getById(@PathVariable int id){
        return this.modelService.getById(id);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateModelRequest updateModelRequest){
        this.modelService.update(updateModelRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.modelService.delete(id);
    }
}