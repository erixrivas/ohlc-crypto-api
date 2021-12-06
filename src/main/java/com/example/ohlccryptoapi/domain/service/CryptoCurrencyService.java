package com.example.ohlccryptoapi.domain.service;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;

import java.util.List;
import java.util.Optional;

public interface CryptoCurrencyService {

    Optional<Cryptocurrency> findCryptoCurrencyById(long id);

    Optional<Cryptocurrency> findCryptoCurrencyByName(String name);

    Cryptocurrency saveCryptoCurrency(Cryptocurrency cryptocurrency);

    List<Cryptocurrency> findAllCryptoCurrency();

    void deleteCryptocurrency(Cryptocurrency cryptocurrency);
}
