package com.example.ohlccryptoapi.api;

import com.example.ohlccryptoapi.domain.service.GetCryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class CryptocurrencyController {
    @Autowired
    GetCryptocurrencyService getCryptocurrencyService;
    @GetMapping("/Cryptocurrency/{name}")
    Mono <GetCryptocurrencyApiResponseDto> getCryptocurrency(
            @AuthenticationPrincipal Mono<Principal> principalMono,
            @PathVariable String name) {
        return principalMono.flatMap (principal -> getCryptocurrencyService.get(principal.getName(),name
        ).map(
                cryptocurrency-> new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())
        ));
    }
}
