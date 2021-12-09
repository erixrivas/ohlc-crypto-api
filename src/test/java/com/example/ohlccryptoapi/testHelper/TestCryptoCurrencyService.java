package com.example.ohlccryptoapi.testHelper;

import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.domain.service.ohlc.CryptoCurrencyService;

import java.util.List;
import java.util.Optional;

public class TestCryptoCurrencyService implements CryptoCurrencyService {
    private TestCryptoCurrencyRepository  testCryptoCurrencyRepository = new TestCryptoCurrencyRepository();

    @Override
    public Optional<Cryptocurrency> findCryptoCurrencyById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cryptocurrency> findCryptoCurrencyByName(String name) {
        return Optional.empty();
    }

    @Override
    public Cryptocurrency saveCryptoCurrency(Cryptocurrency cryptocurrency) {
        return null;
    }

    @Override
    public List<Cryptocurrency> findAllCryptoCurrency() {
        return null;
    }

    @Override
    public void deleteCryptocurrency(Cryptocurrency cryptocurrency) {

    }
}
