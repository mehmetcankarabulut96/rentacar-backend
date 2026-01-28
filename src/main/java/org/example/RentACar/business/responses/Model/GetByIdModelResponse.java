package org.example.RentACar.business.responses.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.RentACar.business.responses.Car.GetCarsByModelResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdModelResponse {
    private int id;
    private String name;
    private String brandName;
    private List<GetCarsByModelResponse> cars;
}