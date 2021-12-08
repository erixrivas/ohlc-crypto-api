package com.example.ohlccryptoapi.domain.Adapter;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;
import com.example.ohlccryptoapi.persistence.service.CryptocurrencyJpaEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class GetCryptocurrencyServiceApiAdapter {

  // final CryptoCurrencyService cryptoCurrencyService;
  @Autowired
   private CryptocurrencyJpaEntityService cryptocurrencyJpaEntityService;
//
//
//    public GetCryptocurrencyServiceApiAdapter(CryptocurrencyJpaEntityService cryptocurrencyJpaEntityService) {
//       this.cryptocurrencyJpaEntityService = cryptocurrencyJpaEntityService;
//   }

    public Mono<Cryptocurrency> get(String username,String name)  {
        return Mono.just(cryptocurrencyJpaEntityService.findCryptoCurrencyByName(name).get());
    }
}
