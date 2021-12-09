package com.example.ohlccryptoapi.persistence.repository.jpa;

import com.example.ohlccryptoapi.persistence.JpaEntity.ExchangeCompanyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeCompanyJpaEntityRepository extends JpaRepository<ExchangeCompanyJpaEntity,Integer> {

    Optional<ExchangeCompanyJpaEntity> findByName(String name);
    Optional<ExchangeCompanyJpaEntity> findById(Integer Id);
}
