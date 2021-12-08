package com.example.ohlccryptoapi.persistence.repository.jpa;

import com.example.ohlccryptoapi.persistence.JpaEntity.CryptocurrencyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptocurrencyJpaEntityRepository extends JpaRepository<CryptocurrencyJpaEntity,Long> {

    Optional<CryptocurrencyJpaEntity> findByName(String name);
    Optional<CryptocurrencyJpaEntity> findById(Long Id);
}
