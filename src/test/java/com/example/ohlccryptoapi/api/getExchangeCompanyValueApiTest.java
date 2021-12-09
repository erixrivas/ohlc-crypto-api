package com.example.ohlccryptoapi.api;

import com.example.ohlccryptoapi.api.ohlc.ExchangeCompanyApiResponseDto;

import com.example.ohlccryptoapi.domain.Adapter.GetCryptocurrencyServiceApiAdapter;
import com.example.ohlccryptoapi.domain.Adapter.OHLCValueServiceApiAdapter;
import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;
import com.example.ohlccryptoapi.domain.Adapter.ExchangeCompanyServiceApiAdapter;
import com.example.ohlccryptoapi.domain.service.ohlc.DomainExchangeCompanyService;
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

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest
public class getExchangeCompanyValueApiTest {
    @Autowired private WebTestClient client;
    @MockBean
    ExchangeCompanyServiceApiAdapter ExchangeCompanyServiceApiAdapter;
    @MockBean
    GetCryptocurrencyServiceApiAdapter getCryptocurrencyServiceApiAdapter;
    @MockBean
    DomainExchangeCompanyService domainExchangeCompanyService;
    @MockBean
    OHLCValueServiceApiAdapter ohlcValueServiceApiAdapter;
    private static Faker faker = Faker.instance();

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private ReactiveAuthenticationManager authenticationManager;

    @Test
    @WithMockUser ("ChuckNorris")
    void getOne(){
        var name = "Bitcoin";
        ExchangeCompany fakeCriptocurrency= getFakeExchangeCompany(name);
        var user="ChuckNorris";
        when(ExchangeCompanyServiceApiAdapter.get(user,name)).thenReturn(Mono.just(fakeCriptocurrency));
        getExchangeCompanyRequest(name)
                //pruebas
                .expectStatus().isOk()
                .expectBody(ExchangeCompanyApiResponseDto.class)
                .value(dto->assertAll(
                                ()->  assertThat(dto.getName()).isEqualTo (name) ,
                                ()->   assertThat(dto.getName()).isEqualTo (fakeCriptocurrency.getName()) ,
                                ()->   assertThat(dto.getDescription()).isEqualTo (fakeCriptocurrency.getDescription())
                ));
    }


    @Test
    @WithMockUser ("ChuckNorris")
    void getAll(){
        var name1 = "XSD";
        ExchangeCompany fakeCriptocurrency1= getFakeExchangeCompany(name1);
        var name2 = "CDF";
        ExchangeCompany fakeCriptocurrency2= getFakeExchangeCompany(name2);

        var user="ChuckNorris";
        var list= Arrays.asList(fakeCriptocurrency1,fakeCriptocurrency2);
        var monoList=Mono.just(list.stream()
                .map( ExchangeCompany->
                        new ExchangeCompanyApiResponseDto(ExchangeCompany.getName(),ExchangeCompany.getDescription())

                ).collect(Collectors.toList()));
        var flux=monoList .flatMapIterable(lista -> lista);
        when(ExchangeCompanyServiceApiAdapter.getAll(user)).thenReturn(monoList);
        getExchangeCompanyRequest()
                //pruebas
                .expectStatus().isOk()
                .expectBodyList(ExchangeCompanyApiResponseDto.class)
                .value(dto->assertAll(
                        ()->  assertThat(dto.size()).isEqualTo (list.size())
                ));
    }

    @Test
    @WithMockUser ("ChuckNorris")
    void getEmpty(){
        var name = "Bitcoin";
        ExchangeCompany fakeCriptocurrency= getFakeExchangeCompany(name);
        var user="ChuckNorris";
        when(ExchangeCompanyServiceApiAdapter.get(user,name)).thenReturn(Mono.empty());
        getExchangeCompanyRequest(name)
                //pruebas
                .expectStatus().isOk().expectBody(Void.class);
    }

    @Test
    @WithAnonymousUser
    void anonymousUserGet(){
        getExchangeCompanyRequest("Bitcoin").expectStatus().isUnauthorized();
    }

    @Test
    void UnauthorizedUserGet(){
        getExchangeCompanyRequest("Bitcoin").expectStatus().isUnauthorized();
    }

    @Test
    void UnauthorizedUserPut(){
        putExchangeCompanyRequest("Bitcoin").expectStatus().isForbidden();
    }


    @Test
    void UnauthorizedGetAll(){
        getExchangeCompanyRequest().expectStatus().isUnauthorized();
    }


    private WebTestClient.ResponseSpec getExchangeCompanyRequest(String name) {
        return client.get().uri("/ExchangeCompany/" + name)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    private WebTestClient.ResponseSpec putExchangeCompanyRequest(String name) {
        return client.put().uri("/ExchangeCompany/" + name)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }



    private WebTestClient.ResponseSpec getExchangeCompanyRequest() {
        return client.get().uri("/ExchangeCompany")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }
    private ExchangeCompany getFakeExchangeCompany(String name) {
        return new ExchangeCompany(faker.random().nextInt(0,100), name, faker.artist().name());
    }


}
