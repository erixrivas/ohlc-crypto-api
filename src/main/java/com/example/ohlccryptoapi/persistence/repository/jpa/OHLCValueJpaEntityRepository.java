package com.example.ohlccryptoapi.persistence.repository.jpa;

import com.example.ohlccryptoapi.domain.model.ohlc.OHLCValue;
import com.example.ohlccryptoapi.persistence.JpaEntity.ExchangeCompanyJpaEntity;
import com.example.ohlccryptoapi.persistence.JpaEntity.OHLCValueJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OHLCValueJpaEntityRepository extends JpaRepository<OHLCValueJpaEntity,Integer> {

    List<OHLCValueJpaEntity> findByUnixTimestamp(Long name);
    List<OHLCValueJpaEntity>findAllByDateBetween(LocalDateTime start, LocalDateTime end);
}
