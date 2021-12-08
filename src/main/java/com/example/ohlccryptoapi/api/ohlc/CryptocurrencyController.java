package com.example.ohlccryptoapi.api.ohlc;

import com.example.ohlccryptoapi.domain.Adapter.GetCryptocurrencyServiceApiAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CryptocurrencyController {
    @Autowired
    GetCryptocurrencyServiceApiAdapter getCryptocurrencyServiceApiAdapter;
    @GetMapping("/Cryptocurrency/{name}")
    Mono <GetCryptocurrencyApiResponseDto> getCryptocurrency(
            @AuthenticationPrincipal Mono<UserDetails> principalMono,
            @PathVariable String name) {
        return principalMono.flatMap (principal -> getCryptocurrencyServiceApiAdapter.get(principal.getUsername(),name)
        ).map(
                cryptocurrency-> new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())
        ).doOnError(e->new RuntimeException(e) );
    }
}
