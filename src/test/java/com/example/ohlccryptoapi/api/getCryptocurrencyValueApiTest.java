package com.example.ohlccryptoapi.api;

import com.example.ohlccryptoapi.domain.model.Cryptocurrency;
import com.example.ohlccryptoapi.domain.service.GetCryptocurrencyService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
    @MockBean  GetCryptocurrencyService getCryptocurrencyService;

    private static Faker faker = Faker.instance();
    @Test
    @WithMockUser ("ChuckNorris")
    void get(){
        String name = "Bitcoin";
        Cryptocurrency fakeCriptocurrency= getFakeCriptocurrency(name);
        String user="ChuckNorris";
        when(getCryptocurrencyService.get(user,name)).thenReturn(Mono.just(fakeCriptocurrency));
        client.get().uri("/Cryptocurrency/"+name)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                //pruebas
                .expectStatus().isOk()
                .expectBody(GetCryptocurrencyApiResponseDto.class)
                .value(dto->assertAll(
                                ()->  assertThat(dto.getName()).isEqualTo (name) ,
                                ()->   assertThat(dto.getName()).isEqualTo (fakeCriptocurrency.getName()) ,
                                ()->   assertThat(dto.getSymbol()).isEqualTo (fakeCriptocurrency.getSymbol())
                ));
    }

    private Cryptocurrency getFakeCriptocurrency(String name) {
        return new Cryptocurrency(name, faker.artist().name());
    }
}
