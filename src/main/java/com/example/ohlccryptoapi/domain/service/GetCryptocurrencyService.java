package com.example.ohlccryptoapi.domain.service;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;
import reactor.core.publisher.Mono;

public class GetCryptocurrencyService {
    public Mono<Cryptocurrency> get(String name) {
        return Mono.empty();
    }
}
