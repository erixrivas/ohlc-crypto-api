package com.example.ohlccryptoapi.api.ohlc;


import com.example.ohlccryptoapi.domain.Adapter.ExchangeCompanyServiceApiAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

@RestController

@RequestMapping("/ExchangeCompany")
public class ExchangeCompanyController {
    @Autowired
    ExchangeCompanyServiceApiAdapter ExchangeCompanyServiceApiAdapter;
    @GetMapping("/{name}")
    Mono <ExchangeCompanyApiResponseDto> getExchangeCompany(
            @ApiIgnore @AuthenticationPrincipal Mono<UserDetails> principalMono,
            @PathVariable String name) {
        return principalMono.flatMap (principal -> ExchangeCompanyServiceApiAdapter.get(principal.getUsername(),name)
        ).map(
                ExchangeCompany-> new ExchangeCompanyApiResponseDto(ExchangeCompany.getName(),ExchangeCompany.getDescription())
        ).doOnError(e->new RuntimeException(e) );



    }
    @GetMapping
    public Flux<ExchangeCompanyApiResponseDto> getAll( @ApiIgnore  @AuthenticationPrincipal Mono<UserDetails> principalMono) {
        System.out.println("::will returns ALL  records::");
        return principalMono.flatMap (principal -> ExchangeCompanyServiceApiAdapter.getAll(principal.getUsername())
        ) .flatMapIterable(list -> list) ;
    }
    @PostMapping
    public Mono<ExchangeCompanyApiResponseDto> save(@ApiIgnore @AuthenticationPrincipal Mono<UserDetails> principalMono ,
                                                    @RequestBody final ExchangeCompanyApiResponseDto ExchangeCompanyApiResponseDto) {
        System.out.println("will insert the record :: "+ ExchangeCompanyApiResponseDto.getName() + " :: " + ExchangeCompanyApiResponseDto.getDescription());
        return principalMono.flatMap (principal -> ExchangeCompanyServiceApiAdapter.save(principal.getUsername(),ExchangeCompanyApiResponseDto)
        ).map(
                ExchangeCompany-> new ExchangeCompanyApiResponseDto(ExchangeCompany.getName(),ExchangeCompany.getDescription())
        ).doOnError(e->new RuntimeException(e) );
    }

    @PutMapping("{name}")
    public Mono<ExchangeCompanyApiResponseDto> updateByname(@ApiIgnore @AuthenticationPrincipal Mono<UserDetails> principalMono ,@PathVariable("name") final String name,
                                                            @RequestBody final  ExchangeCompanyApiResponseDto ExchangeCompanyApiResponseDto) {

        return principalMono.flatMap (principal -> ExchangeCompanyServiceApiAdapter.update(principal.getUsername(),name,ExchangeCompanyApiResponseDto)
        ).map(
                ExchangeCompany-> new ExchangeCompanyApiResponseDto(ExchangeCompany.getName(),ExchangeCompany.getDescription())
        ).doOnError(e->new RuntimeException(e) );




    }
    @DeleteMapping("{name}")
    public Mono<ExchangeCompanyApiResponseDto> delete(@ApiIgnore @AuthenticationPrincipal Mono<UserDetails> principalMono ,@PathVariable("name") final String name) {

        return principalMono.flatMap (principal ->  ExchangeCompanyServiceApiAdapter.delete(principal.getUsername(),name)
        ).map(
                ExchangeCompany-> new ExchangeCompanyApiResponseDto(ExchangeCompany.getName(),ExchangeCompany.getDescription())
        ).doOnError(e->new RuntimeException(e) );

    }











}