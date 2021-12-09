package com.example.ohlccryptoapi.domain.repository.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.OHLCValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OHLCValueRepository {

    Optional<OHLCValue> findById(Integer id);

    List<OHLCValue> findByunixTimestamp(Long name);
    List<OHLCValue> findAllByDate(LocalDateTime start,LocalDateTime end);
    OHLCValue save(OHLCValue OHLCValue);

    List<OHLCValue> findAll();

    void deleteOHLCValue(OHLCValue OHLCValue);
}
