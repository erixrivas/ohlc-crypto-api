package com.example.ohlccryptoapi.domain.model.security;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private Integer id;


    private String username;

    private String password;


    private Set<Role> roles;

    private boolean active = true;




}