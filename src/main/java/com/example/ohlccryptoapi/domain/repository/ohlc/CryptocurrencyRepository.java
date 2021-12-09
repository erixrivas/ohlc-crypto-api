package com.example.ohlccryptoapi.domain.repository.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;

import java.util.List;
import java.util.Optional;

public interface CryptocurrencyRepository {

    Optional<Cryptocurrency> findById(Integer id);

    Optional<Cryptocurrency> findByName(String name);

    Cryptocurrency save(Cryptocurrency cryptocurrency);

    List<Cryptocurrency> findAll();

    void deleteCryptocurrency(Cryptocurrency cryptocurrency);
}
