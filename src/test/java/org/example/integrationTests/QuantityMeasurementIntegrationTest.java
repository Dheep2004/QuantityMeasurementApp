package org.example.integrationTests;

import org.example.QuantityMeasurementApplication;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        classes = QuantityMeasurementApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class QuantityMeasurementIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {

        assertNotNull(
                restTemplate
        );
    }

    @Test
    void applicationStarts() {

        String url =
                "http://localhost:"
                        + port
                        + "/actuator/health";

        String response =
                restTemplate.getForObject(
                        url,
                        String.class
                );

        assertNotNull(
                response
        );
    }
}