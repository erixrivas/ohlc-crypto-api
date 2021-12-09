package com.example.ohlccryptoapi.domain.service.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;

import java.util.List;
import java.util.Optional;

public interface ExchangeCompanyService {

    Optional<ExchangeCompany> findExchangeCompanyById(Integer id);

    Optional<ExchangeCompany> findExchangeCompanyByName(String name);

    ExchangeCompany saveExchangeCompany(ExchangeCompany ExchangeCompany);

    List<ExchangeCompany> findAllExchangeCompany();

    void deleteExchangeCompany(ExchangeCompany ExchangeCompany);
}
