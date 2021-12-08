package com.example.ohlccryptoapi.persistence.service.security;


import com.example.ohlccryptoapi.domain.model.security.Role;
import com.example.ohlccryptoapi.domain.service.security.RoleService;
import com.example.ohlccryptoapi.persistence.JpaEntity.security.RoleJpaEntity;
import com.example.ohlccryptoapi.persistence.repository.jpa.security.RoleJpaEntityRepository;
import com.example.ohlccryptoapi.persistence.service.ICURDSERVICE;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleJpaEntityService implements ICURDSERVICE<Role, RoleJpaEntity, Integer> ,RoleService {
    @Autowired
    private RoleJpaEntityRepository roleJpaEntityRepository;
    @Override
    public Role findRoleByName(String name) {
        return transformToDomainModel(roleJpaEntityRepository.findByName(name));
    }

    @Override
    public RoleJpaEntity transformToEntity(Role domainModel) {
        if (domainModel!=null)

        return new RoleJpaEntity(domainModel.getId(), domainModel.getName(), domainModel.getDescription());
        else return  null;
    }

    @Override
    public Role transformToDomainModel(RoleJpaEntity entity) {
        if (entity!=null)
        return new Role(entity.getId(),entity.getName(),entity.getDescription());
        else return  null        ;
    }

    @Override
    public JpaRepository<RoleJpaEntity, Integer> getRepository() {
        return roleJpaEntityRepository;
    }
}


//
//@Override
//public RoleJpaEntity findByName(String name) {
//        RoleJpaEntity role = roleJpaEntityService.findByName(name);
//        return role;
//        }