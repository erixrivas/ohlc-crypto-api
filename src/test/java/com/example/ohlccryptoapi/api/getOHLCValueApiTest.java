package com.example.ohlccryptoapi.api;

import com.example.ohlccryptoapi.api.ohlc.OHLCValueApiResponseDto;

import com.example.ohlccryptoapi.domain.Adapter.ExchangeCompanyServiceApiAdapter;
import com.example.ohlccryptoapi.domain.Adapter.GetCryptocurrencyServiceApiAdapter;
import com.example.ohlccryptoapi.domain.Adapter.OHLCValueServiceApiAdapter;
import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;
import com.example.ohlccryptoapi.domain.model.ohlc.OHLCValue;

import com.example.ohlccryptoapi.domain.service.ohlc.DomianOHLCValueService;
import com.example.ohlccryptoapi.infrastructure.config.security.JwtTokenProvider;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest
public class getOHLCValueApiTest {
    @Autowired private WebTestClient client;
    @MockBean
    OHLCValueServiceApiAdapter OHLCValueServiceApiAdapter;
    @MockBean
    GetCryptocurrencyServiceApiAdapter getCryptocurrencyServiceApiAdapter;
    @MockBean
    DomianOHLCValueService domainOHLCValueService;
    @MockBean
    ExchangeCompanyServiceApiAdapter exchangeCompanyServiceApiAdapter;

    private static Faker faker = Faker.instance();

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private ReactiveAuthenticationManager authenticationManager;

    @Test
    @WithMockUser ("ChuckNorris")
    void getOne(){
        var id = 1;
        OHLCValue fakeCriptocurrency= getFakeOHLCValue(1);
        var user="ChuckNorris";
        when(OHLCValueServiceApiAdapter.get(user,id)).thenReturn(Mono.just(fakeCriptocurrency));
        getOHLCValueRequest(id)
                //pruebas
                .expectStatus().isOk()
                .expectBody(OHLCValueApiResponseDto.class)
                .value(dto->assertAll(
                                ()->  assertThat(dto.getId()).isEqualTo (id) ,
                                ()->   assertThat(dto.getExchangeCompany().getName()).isEqualTo (fakeCriptocurrency.getExchangeCompany().getName()),
                                ()->   assertThat(dto.getCryptocurrency().getName()).isEqualTo (fakeCriptocurrency.getCryptocurrency().getName())

                ));
    }


    @Test
    @WithMockUser ("ChuckNorris")
    void getAll(){
        var id1 = 1;
        OHLCValue fakeCriptocurrency1= getFakeOHLCValue(id1);
        var id2 = 2;
        OHLCValue fakeCriptocurrency2= getFakeOHLCValue(id2);

        var user="ChuckNorris";
        var list= Arrays.asList(fakeCriptocurrency1,fakeCriptocurrency2);
        var monoList=Mono.just(list.stream()
                .map( oHLCValue->
                        new OHLCValueApiResponseDto(oHLCValue.getId(),oHLCValue.getUnixTimestamp(), oHLCValue.getDate(),
                                oHLCValue.getCryptocurrency(),
                                oHLCValue.getExchangeCompany(),
                                oHLCValue.getOpen(),oHLCValue.getHigh(),oHLCValue.getLow(),oHLCValue.getClose(),oHLCValue.getVolume())

                ).collect(Collectors.toList()));
        var flux=monoList .flatMapIterable(lista -> lista);
        when(OHLCValueServiceApiAdapter.getAll(user)).thenReturn(monoList);
        getOHLCValueRequest()
                //pruebas
                .expectStatus().isOk()
                .expectBodyList(OHLCValueApiResponseDto.class)
                .value(dto->assertAll(
                        ()->  assertThat(dto.size()).isEqualTo (list.size())
                ));
    }

    @Test
    @WithMockUser ("ChuckNorris")
    void getEmpty(){
        var id1 = 1;
        OHLCValue fakeCriptocurrency= getFakeOHLCValue(id1);
        var user="ChuckNorris";
        when(OHLCValueServiceApiAdapter.get(user,id1)).thenReturn(Mono.empty());
        getOHLCValueRequest(id1)
                //pruebas
                .expectStatus().isOk().expectBody(Void.class);
    }

    @Test
    @WithAnonymousUser
    void anonymousUserGet(){
        getOHLCValueRequest(1).expectStatus().isUnauthorized();
    }

    @Test
    void UnauthorizedUserGet(){
        getOHLCValueRequest(1).expectStatus().isUnauthorized();
    }

    @Test
    void UnauthorizedUserPut(){
        putOHLCValueRequest(1).expectStatus().isForbidden();
    }


    @Test
    void UnauthorizedGetAll(){
        getOHLCValueRequest().expectStatus().isUnauthorized();
    }


    private WebTestClient.ResponseSpec getOHLCValueRequest(Integer  id) {
        return client.get().uri("/OHLCValue/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    private WebTestClient.ResponseSpec putOHLCValueRequest(Integer  id) {
        return client.put().uri("/OHLCValue/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }



    private WebTestClient.ResponseSpec getOHLCValueRequest() {
        return client.get().uri("/OHLCValue")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }
    private OHLCValue getFakeOHLCValue(Integer id) {
        return new OHLCValue(id, faker.random().nextLong(),

              LocalDateTime.now(),
                getFakeCriptocurrency(faker.artist().name()),
                getFakeExchangeCompany(faker.artist().name()),
                faker.random().nextDouble(),
                faker.random().nextDouble(),
                faker.random().nextDouble(),
                faker.random().nextDouble(),
                faker.random().nextDouble());
    }
    private Cryptocurrency getFakeCriptocurrency(String name) {
        return new Cryptocurrency(faker.random().nextInt(0,100), name, faker.artist().name());
    }
    private ExchangeCompany getFakeExchangeCompany(String name) {
        return new ExchangeCompany(faker.random().nextInt(0,100), name, faker.artist().name());
    }

}