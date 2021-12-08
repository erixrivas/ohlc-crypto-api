package com.example.ohlccryptoapi.persistence.repository.jpa.security;


import com.example.ohlccryptoapi.persistence.JpaEntity.security.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaEntityRepository extends JpaRepository<UserJpaEntity, Integer> {
    UserJpaEntity findByUsername(String username);
}