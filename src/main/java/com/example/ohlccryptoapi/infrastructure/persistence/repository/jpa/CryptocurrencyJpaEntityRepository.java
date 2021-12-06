package com.example.ohlccryptoapi.infrastructure.persistence.repository.jpa;

import com.example.ohlccryptoapi.infrastructure.persistence.JpaEntity.CryptocurrencyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CryptocurrencyJpaEntityRepository extends JpaRepository<CryptocurrencyJpaEntity,Long> {

    Optional<CryptocurrencyJpaEntity> findByName(String name);
    Optional<CryptocurrencyJpaEntity> findById(String Id);
}
