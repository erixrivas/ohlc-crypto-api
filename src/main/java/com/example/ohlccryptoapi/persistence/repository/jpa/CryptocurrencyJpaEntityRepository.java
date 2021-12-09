package com.example.ohlccryptoapi.persistence.repository.jpa;

import com.example.ohlccryptoapi.persistence.JpaEntity.CryptocurrencyJpaEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptocurrencyJpaEntityRepository extends JpaRepository<CryptocurrencyJpaEntity,Integer> {

    Optional<CryptocurrencyJpaEntity> findByName(String name);
    Optional<CryptocurrencyJpaEntity> findById(Integer Id);
}
