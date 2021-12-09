package com.example.ohlccryptoapi.domain.service.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;

import java.util.List;
import java.util.Optional;

public interface CryptoCurrencyService {

    Optional<Cryptocurrency> findCryptoCurrencyById(Integer id);

    Optional<Cryptocurrency> findCryptoCurrencyByName(String name);

    Cryptocurrency saveCryptoCurrency(Cryptocurrency cryptocurrency);

    List<Cryptocurrency> findAllCryptoCurrency();

    void deleteCryptocurrency(Cryptocurrency cryptocurrency);
}
