package com.example.ohlccryptoapi.domain.service.security;


import com.example.ohlccryptoapi.domain.model.security.Role;

public interface RoleService {
    Role findRoleByName(String name);
}
