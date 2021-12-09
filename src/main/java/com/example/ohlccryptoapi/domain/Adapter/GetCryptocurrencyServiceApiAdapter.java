package com.example.ohlccryptoapi.domain.Adapter;

import com.example.ohlccryptoapi.api.ohlc.GetCryptocurrencyApiResponseDto;
import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.persistence.service.CryptocurrencyJpaEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

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

    public Mono<List<GetCryptocurrencyApiResponseDto>> getAll(String username)  {
        return Mono.just(cryptocurrencyJpaEntityService.findAllCryptoCurrency().stream()
                .map( cryptocurrency->
                        new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())

        ).collect(Collectors.toList()));
    }

    public Mono<Cryptocurrency> save(String username, GetCryptocurrencyApiResponseDto getCryptocurrencyApiResponseDto) {
        return Mono.just(cryptocurrencyJpaEntityService.saveCryptoCurrency
                (new Cryptocurrency(getCryptocurrencyApiResponseDto.getName(),
                        getCryptocurrencyApiResponseDto.getSymbol()) ));
    }

    public Mono<Cryptocurrency> update(String username, String name, GetCryptocurrencyApiResponseDto getCryptocurrencyApiResponseDto) {
      var cryptocurrency =cryptocurrencyJpaEntityService.findCryptoCurrencyByName(name).get();
        cryptocurrency.setName(getCryptocurrencyApiResponseDto.getName());
        cryptocurrency.setSymbol(getCryptocurrencyApiResponseDto.getSymbol());

        var cryptocurrencyUpdate=cryptocurrencyJpaEntityService.saveCryptoCurrency(cryptocurrency);
        return Mono.just(cryptocurrencyUpdate);

    }

    public  Mono<Cryptocurrency> delete(String username,final String name) {
        var cryptocurrencyOptional =cryptocurrencyJpaEntityService.findCryptoCurrencyByName(name);
        if (cryptocurrencyOptional.isEmpty()) {
            return Mono.empty();
        }else {
            var cryptocurrency =cryptocurrencyOptional.get();
            cryptocurrencyJpaEntityService.deleteCryptocurrency(cryptocurrency);
           return  Mono.just(cryptocurrency);
        }

    }
}
