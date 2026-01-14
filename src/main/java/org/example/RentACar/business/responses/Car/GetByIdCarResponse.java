package org.example.RentACar.business.responses.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCarResponse {
    private int id;
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private String state;
    private String modelName;
}