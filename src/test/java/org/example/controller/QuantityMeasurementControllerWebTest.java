package org.example.controller;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.config.SecurityConfig;
import org.example.dto.QuantityDTO;
import org.example.dto.QuantityInputDTO;

import org.example.service.IQuantityMeasurementService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuantityMeasurementController.class)
@Import(SecurityConfig.class)

class QuantityMeasurementControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService service;

    @Test

    @WithMockUser
    void shouldCompare() throws Exception {

        QuantityInputDTO request =
                new QuantityInputDTO(

                        new QuantityDTO(
                                1,
                                "FEET",
                                "LENGTH"
                        ),

                        new QuantityDTO(
                                12,
                                "INCHES",
                                "LENGTH"
                        )
                );

        when(
                service.compare(
                        any(),
                        any()
                )
        ).thenReturn(true);

        mockMvc.perform(

                        post("/api/v1/quantities/compare")

                                .with(csrf())

                                .contentType(MediaType.APPLICATION_JSON)

                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )

                .andExpect(status().isOk());
    }
    }