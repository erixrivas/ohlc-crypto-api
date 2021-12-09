package com.example.ohlccryptoapi.api;

import com.example.ohlccryptoapi.api.ohlc.ExchangeCompanyApiResponseDto;
import com.example.ohlccryptoapi.api.ohlc.GetCryptocurrencyApiResponseDto;
import com.example.ohlccryptoapi.domain.Adapter.ExchangeCompanyServiceApiAdapter;
import com.example.ohlccryptoapi.domain.Adapter.OHLCValueServiceApiAdapter;
import com.example.ohlccryptoapi.domain.model.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.domain.Adapter.GetCryptocurrencyServiceApiAdapter;
import com.example.ohlccryptoapi.domain.model.ohlc.ExchangeCompany;
import com.example.ohlccryptoapi.domain.service.ohlc.DomainCryptoCurrencyService;
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
public class getCryptocurrencyValueApiTest {
    @Autowired private WebTestClient client;
    @MockBean
    GetCryptocurrencyServiceApiAdapter getCryptocurrencyServiceApiAdapter;
    @MockBean
    com.example.ohlccryptoapi.domain.Adapter.ExchangeCompanyServiceApiAdapter ExchangeCompanyServiceApiAdapter;

    @MockBean
    OHLCValueServiceApiAdapter ohlcValueServiceApiAdapter;

    @MockBean
    DomainCryptoCurrencyService domainCryptoCurrencyService;

    private static Faker faker = Faker.instance();

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private ReactiveAuthenticationManager authenticationManager;

    @Test
    @WithMockUser ("ChuckNorris")
    void get(){
        var name = "Bitcoin";
        Cryptocurrency fakeCriptocurrency= getFakeCriptocurrency(name);
        var user="ChuckNorris";
        when(getCryptocurrencyServiceApiAdapter.get(user,name)).thenReturn(Mono.just(fakeCriptocurrency));
        getCryptocurrencyRequest(name)
                //pruebas
                .expectStatus().isOk()
                .expectBody(GetCryptocurrencyApiResponseDto.class)
                .value(dto->assertAll(
                                ()->  assertThat(dto.getName()).isEqualTo (name) ,
                                ()->   assertThat(dto.getName()).isEqualTo (fakeCriptocurrency.getName()) ,
                                ()->   assertThat(dto.getSymbol()).isEqualTo (fakeCriptocurrency.getSymbol())
                ));
    }
    @Test
    @WithMockUser ("ChuckNorris")
    void getEmpty(){
        var name = "Bitcoin";
        Cryptocurrency fakeCriptocurrency= getFakeCriptocurrency(name);
        var user="ChuckNorris";
        when(getCryptocurrencyServiceApiAdapter.get(user,name)).thenReturn(Mono.empty());
        getCryptocurrencyRequest(name)
                //pruebas
                .expectStatus().isOk().expectBody(Void.class);
    }
    @Test
    @WithMockUser ("ChuckNorris")
    void getAll(){
        var name1 = "XSD";
        var fakeCriptocurrency1= getFakeCriptocurrency(name1);
        var name2 = "CDF";
        var fakeCriptocurrency2= getFakeCriptocurrency(name2);

        var user="ChuckNorris";
        var list= Arrays.asList(fakeCriptocurrency1,fakeCriptocurrency2);
        var monoList=Mono.just(list.stream()
                .map( ExchangeCompany->
                        new GetCryptocurrencyApiResponseDto(ExchangeCompany.getName(),ExchangeCompany.getSymbol())

                ).collect(Collectors.toList()));
        var flux=monoList .flatMapIterable(lista -> lista);
        when(getCryptocurrencyServiceApiAdapter.getAll(user)).thenReturn(monoList);
        getCryptocurrencyRequest()
                //pruebas
                .expectStatus().isOk()
                .expectBodyList(GetCryptocurrencyApiResponseDto.class)
                .value(dto->assertAll(
                        ()->  assertThat(dto.size()).isEqualTo (list.size())
                ));
    }
    @Test
    @WithAnonymousUser
    void anonymousUserGet(){
        getCryptocurrencyRequest("Bitcoin").expectStatus().isUnauthorized();
    }

    @Test
    void UnauthorizedUserGet(){
        getCryptocurrencyRequest("Bitcoin").expectStatus().isUnauthorized();
    }


    private WebTestClient.ResponseSpec getCryptocurrencyRequest(String name) {
        return client.get().uri("/Cryptocurrency/" + name)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    private WebTestClient.ResponseSpec getCryptocurrencyRequest() {
        return client.get().uri("/Cryptocurrency")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }
    private Cryptocurrency getFakeCriptocurrency(String name) {
        return new Cryptocurrency(faker.random().nextInt(0,100), name, faker.artist().name());
    }


}
