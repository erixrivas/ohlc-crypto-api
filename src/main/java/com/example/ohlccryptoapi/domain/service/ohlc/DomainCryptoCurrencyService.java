package com.example.ohlccryptoapi.domain.service.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.domain.repository.ohlc.CryptocurrencyRepository;

import java.util.List;
import java.util.Optional;

public class DomainCryptoCurrencyService implements  CryptoCurrencyService{

    private final CryptocurrencyRepository cryptocurrencyRepository;

    public DomainCryptoCurrencyService(CryptocurrencyRepository cryptocurrencyRepository) {
        this.cryptocurrencyRepository = cryptocurrencyRepository;
    }

    @Override
    public Optional<Cryptocurrency> findCryptoCurrencyById(Integer id) {
        return cryptocurrencyRepository.findById(id);
    }

    @Override
    public Optional<Cryptocurrency> findCryptoCurrencyByName(String name) {
        return cryptocurrencyRepository.findByName(name);
    }

    @Override
    public Cryptocurrency saveCryptoCurrency(Cryptocurrency cryptocurrency) {
        return cryptocurrencyRepository.save(cryptocurrency);
    }

    @Override
    public List<Cryptocurrency> findAllCryptoCurrency() {
        return cryptocurrencyRepository.findAll();
    }

    @Override
    public void deleteCryptocurrency(Cryptocurrency cryptocurrency) {
        cryptocurrencyRepository.deleteCryptocurrency( cryptocurrency);
    }
}
