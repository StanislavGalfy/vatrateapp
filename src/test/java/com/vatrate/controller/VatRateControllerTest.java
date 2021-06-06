package com.vatrate.controller;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.core.IsNull.nullValue;

import com.google.common.io.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class VatRateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getVatRateStatisticsSuccessfulTest() throws Exception {
        String vatRateServerResponseJson = Resources.toString(Resources.getResource(
                "json/vatrate/vatRateSourceResponse.json"), StandardCharsets.UTF_8);

        mockServer.expect(ExpectedCount.once(), requestTo(any(String.class)))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(vatRateServerResponseJson)
                );

        mockMvc.perform(get("/vatrate/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lowestVatRateCountry").value("Malta"))
                .andExpect(jsonPath("$.secondLowestVatRateCountry").value("Cyprus"))
                .andExpect(jsonPath("$.highestVatRateCountry").value("Hungary"))
                .andExpect(jsonPath("$.secondHighestVatRateCountry").value("Sweden"))
                .andExpect(jsonPath("$.errorMessage").value(nullValue()));

        mockServer.verify();
    }

    @Test
    public void getVatRateStatisticsFailedTest() throws Exception {
        mockServer.expect(ExpectedCount.once(), requestTo(any(String.class)))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/vatrate/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lowestVatRateCountry").value(nullValue()))
                .andExpect(jsonPath("$.secondLowestVatRateCountry").value(nullValue()))
                .andExpect(jsonPath("$.highestVatRateCountry").value(nullValue()))
                .andExpect(jsonPath("$.secondHighestVatRateCountry").value(nullValue()))
                .andExpect(jsonPath("$.errorMessage").value(notNullValue()));

        mockServer.verify();
    }
}
