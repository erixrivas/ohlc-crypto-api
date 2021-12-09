package com.example.ohlccryptoapi.api.ohlc;

import com.example.ohlccryptoapi.domain.Adapter.OHLCValueServiceApiAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

@RequestMapping("/OHLCValue")
public class OHLCValueController {
    @Autowired
    OHLCValueServiceApiAdapter OHLCValueServiceApiAdapter;
    @GetMapping("/{id}")
    Mono <OHLCValueApiResponseDto> getOHLCValue(
            @AuthenticationPrincipal Mono<UserDetails> principalMono,
            @PathVariable Integer id) {
        return principalMono.flatMap (principal -> OHLCValueServiceApiAdapter.get(principal.getUsername(),id)
        ).map(
                oHLCValue-> new OHLCValueApiResponseDto(oHLCValue.getId(),oHLCValue.getUnixTimestamp(), oHLCValue.getDate(),
                       oHLCValue.getCryptocurrency(),
                       oHLCValue.getExchangeCompany(),
                        oHLCValue.getOpen(),oHLCValue.getHigh(),oHLCValue.getLow(),oHLCValue.getClose(),oHLCValue.getVolume())
        ).doOnError(e->new RuntimeException(e) );



    }
    @GetMapping
    public Flux<OHLCValueApiResponseDto> getAll(  @AuthenticationPrincipal Mono<UserDetails> principalMono) {
        System.out.println("::will returns ALL  records::");
        return principalMono.flatMap (principal -> OHLCValueServiceApiAdapter.getAll(principal.getUsername())
        ) .flatMapIterable(list -> list) ;
    }
    @PostMapping
    public Mono<OHLCValueApiResponseDto> save(@AuthenticationPrincipal Mono<UserDetails> principalMono ,
                                                    @RequestBody final OHLCValueApiResponseDto OHLCValueApiResponseDto) {
        System.out.println("will insert the record :: "+ OHLCValueApiResponseDto.getId() + " :: " + OHLCValueApiResponseDto.getUnixTimestamp());
        return principalMono.flatMap (principal -> OHLCValueServiceApiAdapter.save(principal.getUsername(),OHLCValueApiResponseDto)
        ).map(
                oHLCValue-> new OHLCValueApiResponseDto(oHLCValue.getId(),oHLCValue.getUnixTimestamp(), oHLCValue.getDate(),
                        oHLCValue.getCryptocurrency(),
                        oHLCValue.getExchangeCompany(),
                        oHLCValue.getOpen(),oHLCValue.getHigh(),oHLCValue.getLow(),oHLCValue.getClose(),oHLCValue.getVolume())
        ).doOnError(e->new RuntimeException(e) );
    }

    @PutMapping("{id}")
    public Mono<OHLCValueApiResponseDto> updateByname(@AuthenticationPrincipal Mono<UserDetails> principalMono ,@PathVariable("name") final Integer id,
                                                            @RequestBody final  OHLCValueApiResponseDto OHLCValueApiResponseDto) {

        return principalMono.flatMap (principal -> OHLCValueServiceApiAdapter.update(principal.getUsername(),id,OHLCValueApiResponseDto)
        ).map(
                oHLCValue-> new OHLCValueApiResponseDto(oHLCValue.getId(),oHLCValue.getUnixTimestamp(), oHLCValue.getDate(),
                        oHLCValue.getCryptocurrency(),
                        oHLCValue.getExchangeCompany(),
                        oHLCValue.getOpen(),oHLCValue.getHigh(),oHLCValue.getLow(),oHLCValue.getClose(),oHLCValue.getVolume())
        ).doOnError(e->new RuntimeException(e) );




    }
    @DeleteMapping("{id}")
    public Mono<OHLCValueApiResponseDto> delete(@AuthenticationPrincipal Mono<UserDetails> principalMono ,@PathVariable("name") final Integer id) {

        return principalMono.flatMap (principal ->  OHLCValueServiceApiAdapter.delete(principal.getUsername(),id)
        ).map(
                oHLCValue-> new OHLCValueApiResponseDto(oHLCValue.getId(),oHLCValue.getUnixTimestamp(), oHLCValue.getDate(),
                        oHLCValue.getCryptocurrency(),
                        oHLCValue.getExchangeCompany(),
                        oHLCValue.getOpen(),oHLCValue.getHigh(),oHLCValue.getLow(),oHLCValue.getClose(),oHLCValue.getVolume())
        ).doOnError(e->new RuntimeException(e) );

    }











}