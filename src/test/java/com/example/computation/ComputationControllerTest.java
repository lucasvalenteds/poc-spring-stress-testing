package com.example.computation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ComputationControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void runningCPUBoundComputation() {
        webTestClient.get()
                .uri("/computations/cpu")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NO_CONTENT)
                .expectBody().isEmpty();
    }
}
