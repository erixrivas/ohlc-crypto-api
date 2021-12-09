package com.example.ohlccryptoapi.domain.service.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.OHLCValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OHLCValueService {

    Optional<OHLCValue> findOHLCValueById(Integer id);

    List<OHLCValue> findOHLCValueByUnixTimestamp(Long name);
    List<OHLCValue>findAllByDateOHLCValue(LocalDateTime start, LocalDateTime end);
    OHLCValue saveOHLCValue(OHLCValue OHLCValue);

    List<OHLCValue> findAllOHLCValue();

    void deleteOHLCValue(OHLCValue OHLCValue);
}
