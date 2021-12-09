package com.example.ohlccryptoapi.domain.model.security.ohlc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeCompany {
    private Integer id;
    private String name;
    private String description;

    public ExchangeCompany(String name, String description) {
        this.name = name;
       this.description = description;
    }
}
