package com.example.ohlccryptoapi.persistence.service.security;


import com.example.ohlccryptoapi.domain.model.security.Role;
import com.example.ohlccryptoapi.domain.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleJpaEntityService implements RoleService {

    @Override
    public Role findRoleByName(String name) {
        return null;
    }
}

@Autowired
private RoleJpaEntity;

@Override
public RoleJpaEntity findByName(String name) {
        RoleJpaEntity role = roleJpaEntityService.findByName(name);
        return role;
        }