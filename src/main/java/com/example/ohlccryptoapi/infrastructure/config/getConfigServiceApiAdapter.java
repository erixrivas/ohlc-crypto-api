package com.example.ohlccryptoapi.infrastructure.config;

import com.example.ohlccryptoapi.OhlcCryptoApiApplication;
import com.example.ohlccryptoapi.domain.repository.CryptocurrencyRepository;
import com.example.ohlccryptoapi.domain.service.CryptoCurrencyService;
import com.example.ohlccryptoapi.domain.service.DomainCryptoCurrencyService;
import com.example.ohlccryptoapi.infrastructure.persistence.service.CryptocurrencyJpaEntityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = OhlcCryptoApiApplication.class)
public class getConfigServiceApiAdapter {

//    @Bean
//    CryptoCurrencyService cryptoCurrencyService(final CryptocurrencyRepository cryptocurrencyRepository) {
//        return new DomainCryptoCurrencyService(cryptocurrencyRepository);
//    }




//
//    GetCryptocurrencyServiceApiAdapter GetCryptocurrencyServiceApiAdapter(){
//    return     new GetCryptocurrencyServiceApiAdapter(new TestCryptoCurrencyService());
//    }
//    @Bean
//    GetCryptocurrencyServiceApiAdapter GetCryptocurrencyServiceApiAdapter(CryptoCurrencyService cryptoCurrencyService){
//        return     new GetCryptocurrencyServiceApiAdapter(cryptoCurrencyService);
//    }
}
