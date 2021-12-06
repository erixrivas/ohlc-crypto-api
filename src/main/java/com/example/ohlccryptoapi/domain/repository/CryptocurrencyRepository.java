package com.example.ohlccryptoapi.domain.repository;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;

import java.util.List;
import java.util.Optional;

public interface CryptocurrencyRepository {

    Optional<Cryptocurrency> findById(long id);

    Optional<Cryptocurrency> findByName(String name);

    Cryptocurrency save(Cryptocurrency cryptocurrency);

    List<Cryptocurrency> findAll();

    void deleteCryptocurrency(Cryptocurrency cryptocurrency);
}
