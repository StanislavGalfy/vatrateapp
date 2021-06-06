package com.client.vatrate;

import com.dto.vatrate.VatRatesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Slf4j
public class VatRateSourceClient {

    private final String vatRatesSourceAddress;
    private final RestTemplate restTemplate;

    public VatRateSourceClient (
            RestTemplate restTemplate,
            @Value("${vat.rates.source.address}") final String vatRatesServerAddress
    ) {
        this.restTemplate = restTemplate;
        this.vatRatesSourceAddress = vatRatesServerAddress;
    }

    public Optional<VatRatesDto> getVatRatesDto() {
        try {
            return Optional.of(restTemplate.getForObject(vatRatesSourceAddress, VatRatesDto.class));
        } catch (Exception e) {
            log.error("Error loading VAT rates from " + vatRatesSourceAddress, e);
            return Optional.empty();
        }
    }
}
