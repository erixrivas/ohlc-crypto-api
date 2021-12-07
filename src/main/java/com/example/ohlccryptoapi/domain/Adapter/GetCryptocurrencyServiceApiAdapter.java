package com.example.ohlccryptoapi.domain.Adapter;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;
import com.example.ohlccryptoapi.domain.service.CryptoCurrencyService;
import com.example.ohlccryptoapi.domain.service.DomainCryptoCurrencyService;
import com.example.ohlccryptoapi.infrastructure.persistence.service.CryptocurrencyJpaEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class GetCryptocurrencyServiceApiAdapter {

  // final CryptoCurrencyService cryptoCurrencyService;
   final CryptocurrencyJpaEntityService cryptocurrencyJpaEntityService;
//
  @Autowired
    public GetCryptocurrencyServiceApiAdapter(CryptocurrencyJpaEntityService cryptocurrencyJpaEntityService) {
       this.cryptocurrencyJpaEntityService = cryptocurrencyJpaEntityService;
   }

    public Mono<Cryptocurrency> get(String user,String name) {
        return Mono.just(cryptocurrencyJpaEntityService.findCryptoCurrencyByName(name).get());
    }
}
