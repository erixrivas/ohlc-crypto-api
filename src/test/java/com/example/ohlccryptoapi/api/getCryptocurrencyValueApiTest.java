package com.example.ohlccryptoapi.api;

import com.example.ohlccryptoapi.api.ohlc.GetCryptocurrencyApiResponseDto;
import com.example.ohlccryptoapi.domain.model.security.ohlc.Cryptocurrency;
import com.example.ohlccryptoapi.domain.Adapter.GetCryptocurrencyServiceApiAdapter;
import com.example.ohlccryptoapi.domain.service.DomainCryptoCurrencyService;
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

    private Cryptocurrency getFakeCriptocurrency(String name) {
        return new Cryptocurrency(faker.random().nextInt(0,100), name, faker.artist().name());
    }


}
