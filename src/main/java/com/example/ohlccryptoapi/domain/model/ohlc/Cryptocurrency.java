package com.example.ohlccryptoapi.domain.model.ohlc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocurrency {
    private Integer id;
    private String name;
    private String Symbol;

    public Cryptocurrency(String name, String symbol) {
        this.name = name;
        Symbol = symbol;
    }
}
