package com.example.ohlccryptoapi.testHelper;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;
import com.example.ohlccryptoapi.domain.repository.CryptocurrencyRepository;

import java.util.List;
import java.util.Optional;

public class TestCryptoCurrencyRepository implements CryptocurrencyRepository {
    @Override
    public Optional<Cryptocurrency> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cryptocurrency> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Cryptocurrency save(Cryptocurrency cryptocurrency) {
        return null;
    }

    @Override
    public List<Cryptocurrency> findAll() {
        return null;
    }

    @Override
    public void deleteCryptocurrency(Cryptocurrency cryptocurrency) {

    }
}
