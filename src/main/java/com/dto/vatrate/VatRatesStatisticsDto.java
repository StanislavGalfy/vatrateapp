package com.dto.vatrate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VatRatesStatisticsDto {

    private String lowestVatRateCountry;

    private String secondLowestVatRateCountry;

    private String highestVatRateCountry;

    private String secondHighestVatRateCountry;

    private String errorMessage;
}
