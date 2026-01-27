package org.example.RentACar.business.responses.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelsByBrandResponse {
    private int id;
    private String name;
}