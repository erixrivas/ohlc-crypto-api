package com.example.ohlccryptoapi.domain.Adapter;

import com.example.ohlccryptoapi.api.ohlc.OHLCValueApiResponseDto;

import com.example.ohlccryptoapi.domain.model.ohlc.OHLCValue;
import com.example.ohlccryptoapi.persistence.service.CryptocurrencyJpaEntityService;
import com.example.ohlccryptoapi.persistence.service.ExchangeCompanyJpaEntityService;
import com.example.ohlccryptoapi.persistence.service.OHLCValueJpaEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OHLCValueServiceApiAdapter {

    // final OHLCValueService OHLCValueService;
    @Autowired
    private OHLCValueJpaEntityService OHLCValueJpaEntityService;

    @Autowired
    private ExchangeCompanyJpaEntityService exchangeCompanyJpaEntityService;

    @Autowired
    private CryptocurrencyJpaEntityService cryptocurrencyJpaEntityService;

//
//
//    public OHLCValueServiceApiAdapter(OHLCValueJpaEntityService OHLCValueJpaEntityService) {
//       this.OHLCValueJpaEntityService = OHLCValueJpaEntityService;
//   }

    public Mono<OHLCValue> get(String username,Integer id)  {
        return Mono.just(OHLCValueJpaEntityService.findOHLCValueById(id).get());
    }

    public Mono<List<OHLCValueApiResponseDto>> getAll(String username)  {
        return Mono.just(OHLCValueJpaEntityService.findAllOHLCValue().stream()
                .map( oHLCValue->
                        new OHLCValueApiResponseDto(oHLCValue.getId(),oHLCValue.getUnixTimestamp(), oHLCValue.getDate(),
                                oHLCValue.getCryptocurrency(),
                                oHLCValue.getExchangeCompany(),
                                oHLCValue.getOpen(),oHLCValue.getHigh(),oHLCValue.getLow(),oHLCValue.getClose(),oHLCValue.getVolume())

                ).collect(Collectors.toList()));
    }

    public Mono<OHLCValue> save(String username, OHLCValueApiResponseDto oHLCValueApiResponseDto) {
        return Mono.just(OHLCValueJpaEntityService.saveOHLCValue
                (new OHLCValue(oHLCValueApiResponseDto.getId(),oHLCValueApiResponseDto.getUnixTimestamp(), oHLCValueApiResponseDto.getDate(),
                        cryptocurrencyJpaEntityService.findCryptoCurrencyByName(oHLCValueApiResponseDto.getCryptocurrency().getName()).get() ,
                        exchangeCompanyJpaEntityService.findExchangeCompanyByName(oHLCValueApiResponseDto.getExchangeCompany().getName()).get(),

                        oHLCValueApiResponseDto.getOpen(),oHLCValueApiResponseDto.getHigh(),oHLCValueApiResponseDto.getLow(),oHLCValueApiResponseDto.getClose(),oHLCValueApiResponseDto.getVolume()) ));
    }

    public Mono<OHLCValue> update(String username, Integer id , OHLCValueApiResponseDto OHLCValueApiResponseDto) {
        var OHLCValue =OHLCValueJpaEntityService.findOHLCValueById(id).get();
       /*
       *
       * */
        var OHLCValueUpdate=OHLCValueJpaEntityService.saveOHLCValue(OHLCValue);
        return Mono.just(OHLCValueUpdate);

    }

    public  Mono<OHLCValue> delete(String username,final Integer id) {
        var OHLCValueOptional =OHLCValueJpaEntityService.findOHLCValueById(id);
        if (OHLCValueOptional.isEmpty()) {
            return Mono.empty();
        }else {
            var OHLCValue =OHLCValueOptional.get();
            OHLCValueJpaEntityService.deleteOHLCValue(OHLCValue);
            return  Mono.just(OHLCValue);
        }

    }
}