package com.dto.vatrate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tools.vatrate.VatRatesDtoDeserializer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(using = VatRatesDtoDeserializer.class)
public class VatRatesDto {

    private List<VatRate> vatRates;
}
