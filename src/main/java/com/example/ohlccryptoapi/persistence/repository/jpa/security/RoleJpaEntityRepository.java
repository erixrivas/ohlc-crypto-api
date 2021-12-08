package com.example.ohlccryptoapi.persistence.repository.jpa.security;

import com.akhianand.springrolejwt.model.Role;
import com.example.ohlccryptoapi.persistence.JpaEntity.security.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaEntityRepository extends JpaRepository<RoleJpaEntity, Long> {
    RoleJpaEntity findByName(String name);
}