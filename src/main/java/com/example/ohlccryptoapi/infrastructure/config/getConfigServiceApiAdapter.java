package com.example.ohlccryptoapi.infrastructure.config;

import com.example.ohlccryptoapi.OhlcCryptoApiApplication;
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
