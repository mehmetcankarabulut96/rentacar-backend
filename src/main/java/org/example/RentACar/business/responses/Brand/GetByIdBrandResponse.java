package org.example.RentACar.business.responses.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.RentACar.business.responses.Model.GetModelsByBrandResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdBrandResponse {
    private int id;
    private String name;
    private List<GetModelsByBrandResponse> models;
}