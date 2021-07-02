package com.anderson.dockerh2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse {

    private Double mount;

    private Double mount_exchange;

    private String currency_origin;

    private String currency_destiny;

    private String exchange_rate;

}
