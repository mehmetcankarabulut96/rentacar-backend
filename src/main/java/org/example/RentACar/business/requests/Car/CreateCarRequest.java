package org.example.RentACar.business.requests.Car;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private int modelId;
}