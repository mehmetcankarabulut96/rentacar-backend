package org.example.RentACar.business.requests.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.RentACar.entities.enums.CarState;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    private int id;
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private CarState state;
    private boolean deleted;
    private String modelName;
}