package com.example.ohlccryptoapi.api;

import com.example.ohlccryptoapi.domain.service.GetCryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CryptocurrencyController {
    @Autowired
    GetCryptocurrencyService getCryptocurrencyService;
    @GetMapping("/Cryptocurrency/{name}")
    Mono <GetCryptocurrencyApiResponseDto> getCryptocurrency(
            @PathVariable String name) {
        return getCryptocurrencyService.get(name).map(
                cryptocurrency-> new GetCryptocurrencyApiResponseDto(cryptocurrency.getName(),cryptocurrency.getSymbol())
        );
    }
}
