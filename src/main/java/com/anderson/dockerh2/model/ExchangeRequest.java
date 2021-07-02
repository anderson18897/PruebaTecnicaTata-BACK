package com.anderson.dockerh2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {

    private Double mount;

    private String currency_origin;

    private String currency_destiny;

}
