package com.example.ohlccryptoapi.api.security;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = CurrentUserController.class)
@Slf4j
class CurrentUserControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    @WithMockUser()
    void testCurrentUser() {
        this.client.get()
                .uri("/CurrentUserController")
                .exchange()
                .expectBody()
                .jsonPath("$.name").isEqualTo("user")
                .jsonPath("$.roles").isArray()
                .jsonPath("$.roles[0]").isEqualTo("ROLE_USER");
    }

}
