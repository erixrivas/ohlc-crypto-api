package com.example.ohlccryptoapi.domain.repository.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;

import java.util.List;
import java.util.Optional;

public interface ExchangeCompanyRepository {

    Optional<ExchangeCompany> findById(Integer id);

    Optional<ExchangeCompany> findByName(String name);

    ExchangeCompany save(ExchangeCompany ExchangeCompany);

    List<ExchangeCompany> findAll();

    void deleteExchangeCompany(ExchangeCompany ExchangeCompany);
}
