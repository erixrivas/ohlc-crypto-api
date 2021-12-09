package com.example.ohlccryptoapi.domain.service.ohlc;

import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;
import com.example.ohlccryptoapi.domain.repository.ohlc.ExchangeCompanyRepository;

import java.util.List;
import java.util.Optional;

public class DomainExchangeCompanyService implements  ExchangeCompanyService{

    private final ExchangeCompanyRepository ExchangeCompanyRepository;

    public DomainExchangeCompanyService(ExchangeCompanyRepository ExchangeCompanyRepository) {
        this.ExchangeCompanyRepository = ExchangeCompanyRepository;
    }

    @Override
    public Optional<ExchangeCompany> findExchangeCompanyById(Integer id) {
        return ExchangeCompanyRepository.findById(id);
    }

    @Override
    public Optional<ExchangeCompany> findExchangeCompanyByName(String name) {
        return ExchangeCompanyRepository.findByName(name);
    }

    @Override
    public ExchangeCompany saveExchangeCompany(ExchangeCompany ExchangeCompany) {
        return ExchangeCompanyRepository.save(ExchangeCompany);
    }

    @Override
    public List<ExchangeCompany> findAllExchangeCompany() {
        return ExchangeCompanyRepository.findAll();
    }

    @Override
    public void deleteExchangeCompany(ExchangeCompany ExchangeCompany) {
        ExchangeCompanyRepository.deleteExchangeCompany( ExchangeCompany);
    }
}
