package com.example.ohlccryptoapi.domain.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role {


    private Integer id;


    private String name;


    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
