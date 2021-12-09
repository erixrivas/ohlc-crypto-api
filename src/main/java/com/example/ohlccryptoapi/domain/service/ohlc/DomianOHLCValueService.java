package com.example.ohlccryptoapi.domain.service.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.OHLCValue;
import com.example.ohlccryptoapi.domain.repository.ohlc.OHLCValueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DomianOHLCValueService implements OHLCValueService{
    private final OHLCValueRepository ohlcValueRepository;

    public DomianOHLCValueService(OHLCValueRepository ohlcValueRepository) {
        this.ohlcValueRepository = ohlcValueRepository;
    }

    @Override
    public Optional<OHLCValue> findOHLCValueById(Integer id) {
        return ohlcValueRepository.findById(id);
    }

    @Override
    public List<OHLCValue> findOHLCValueByUnixTimestamp(Long unixTimestamp) {
        return ohlcValueRepository.findByunixTimestamp(unixTimestamp);
    }

    @Override
    public List<OHLCValue> findAllByDateOHLCValue(LocalDateTime start, LocalDateTime end) {
        return ohlcValueRepository.findAllByDate(start,end);
    }

    @Override
    public OHLCValue saveOHLCValue(OHLCValue OHLCValue) {
        return ohlcValueRepository.save(OHLCValue);
    }

    @Override
    public List<OHLCValue> findAllOHLCValue() {
        return ohlcValueRepository.findAll();
    }

    @Override
    public void deleteOHLCValue(OHLCValue OHLCValue) {
    ohlcValueRepository.deleteOHLCValue(OHLCValue);
    }
}
