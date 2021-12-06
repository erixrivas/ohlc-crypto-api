package com.example.ohlccryptoapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocurrency {
    private long id;
    private String name;
    private String Symbol;

}
