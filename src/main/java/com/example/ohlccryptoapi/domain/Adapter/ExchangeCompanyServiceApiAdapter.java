package com.example.ohlccryptoapi.domain.Adapter;

import com.example.ohlccryptoapi.api.ohlc.ExchangeCompanyApiResponseDto;

import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;
import com.example.ohlccryptoapi.persistence.service.ExchangeCompanyJpaEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ExchangeCompanyServiceApiAdapter {

    // final ExchangeCompanyService ExchangeCompanyService;
    @Autowired
    private ExchangeCompanyJpaEntityService ExchangeCompanyJpaEntityService;
//
//
//    public ExchangeCompanyServiceApiAdapter(ExchangeCompanyJpaEntityService ExchangeCompanyJpaEntityService) {
//       this.ExchangeCompanyJpaEntityService = ExchangeCompanyJpaEntityService;
//   }

    public Mono<ExchangeCompany> get(String username,String name)  {
        return Mono.just(ExchangeCompanyJpaEntityService.findExchangeCompanyByName(name).get());
    }

    public Mono<List<ExchangeCompanyApiResponseDto>> getAll(String username)  {
        return Mono.just(ExchangeCompanyJpaEntityService.findAllExchangeCompany().stream()
                .map( ExchangeCompany->
                        new ExchangeCompanyApiResponseDto(ExchangeCompany.getName(),ExchangeCompany.getDescription())

                ).collect(Collectors.toList()));
    }

    public Mono<ExchangeCompany> save(String username, ExchangeCompanyApiResponseDto ExchangeCompanyApiResponseDto) {
        return Mono.just(ExchangeCompanyJpaEntityService.saveExchangeCompany
                (new ExchangeCompany(ExchangeCompanyApiResponseDto.getName(),
                        ExchangeCompanyApiResponseDto.getDescription()) ));
    }

    public Mono<ExchangeCompany> update(String username, String name, ExchangeCompanyApiResponseDto ExchangeCompanyApiResponseDto) {
        var ExchangeCompany =ExchangeCompanyJpaEntityService.findExchangeCompanyByName(name).get();
        ExchangeCompany.setName(ExchangeCompanyApiResponseDto.getName());
        ExchangeCompany.setDescription(ExchangeCompanyApiResponseDto.getDescription());

        var ExchangeCompanyUpdate=ExchangeCompanyJpaEntityService.saveExchangeCompany(ExchangeCompany);
        return Mono.just(ExchangeCompanyUpdate);

    }

    public  Mono<ExchangeCompany> delete(String username,final String name) {
        var ExchangeCompanyOptional =ExchangeCompanyJpaEntityService.findExchangeCompanyByName(name);
        if (ExchangeCompanyOptional.isEmpty()) {
            return Mono.empty();
        }else {
            var ExchangeCompany =ExchangeCompanyOptional.get();
            ExchangeCompanyJpaEntityService.deleteExchangeCompany(ExchangeCompany);
            return  Mono.just(ExchangeCompany);
        }

    }
}