package com.example.ohlccryptoapi.api.ohlc;

import com.example.ohlccryptoapi.domain.Adapter.GetCryptocurrencyServiceApiAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

@RequestMapping("/Cryptocurrency")
public class CryptocurrencyController {
    @Autowired
    GetCryptocurrencyServiceApiAdapter getCryptocurrencyServiceApiAdapter;
    @GetMapping("/{name}")
    Mono <GetCryptocurrencyApiResponseDto> getCryptocurrency(
            @AuthenticationPrincipal Mono<UserDetails> principalMono,
            @PathVariable String name) {
        return principalMono.flatMap (principal -> getCryptocurrencyServiceApiAdapter.get(principal.getUsername(),name)
        ).map(
                cryptocurrency-> new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())
        ).doOnError(e->new RuntimeException(e) );



    }
    @GetMapping
    public Flux<GetCryptocurrencyApiResponseDto> getAll(  @AuthenticationPrincipal Mono<UserDetails> principalMono) {
        System.out.println("::will returns ALL Students records::");
        return principalMono.flatMap (principal -> getCryptocurrencyServiceApiAdapter.getAll(principal.getUsername())
        ) .flatMapIterable(list -> list) ;
    }
    @PostMapping
    public Mono<GetCryptocurrencyApiResponseDto> save(@AuthenticationPrincipal Mono<UserDetails> principalMono ,
                     @RequestBody final GetCryptocurrencyApiResponseDto getCryptocurrencyApiResponseDto) {
        System.out.println("will insert the record :: "+ getCryptocurrencyApiResponseDto.getName() + " :: " + getCryptocurrencyApiResponseDto.getSymbol());
        return principalMono.flatMap (principal -> getCryptocurrencyServiceApiAdapter.save(principal.getUsername(),getCryptocurrencyApiResponseDto)
        ).map(
                cryptocurrency-> new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())
        ).doOnError(e->new RuntimeException(e) );
    }

    @PutMapping("{name}")
    public Mono<GetCryptocurrencyApiResponseDto> updateByname(@AuthenticationPrincipal Mono<UserDetails> principalMono ,@PathVariable("name") final String name,
                             @RequestBody final  GetCryptocurrencyApiResponseDto getCryptocurrencyApiResponseDto) {
        System.out.println("::update the Student record::");
        return principalMono.flatMap (principal -> getCryptocurrencyServiceApiAdapter.update(principal.getUsername(),name,getCryptocurrencyApiResponseDto)
        ).map(
                cryptocurrency-> new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())
        ).doOnError(e->new RuntimeException(e) );




    }
    @DeleteMapping("{name}")
    public Mono<GetCryptocurrencyApiResponseDto> delete(@AuthenticationPrincipal Mono<UserDetails> principalMono ,@PathVariable("name") final String name) {
        System.out.println("::update the Student record::");
        return principalMono.flatMap (principal ->  getCryptocurrencyServiceApiAdapter.delete(principal.getUsername(),name)
        ).map(
                cryptocurrency-> new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())
        ).doOnError(e->new RuntimeException(e) );

    }











}
