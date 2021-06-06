package com.service.vatrate;

import com.client.vatrate.VatRateSourceClient;
import com.dto.vatrate.VatRatesStatisticsDto;
import com.tools.vatrate.VatRateComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

import lombok.val;

@Service
@RequiredArgsConstructor
public class VatRateService {

    private final VatRateComparator vatRateComparator;
    private final VatRateSourceClient vatRateSourceClient;

    public VatRatesStatisticsDto getVatRateStatistic() {
        val vatRatesDtoOptional = vatRateSourceClient.getVatRatesDto();
        if (vatRatesDtoOptional.isEmpty()) {
            return VatRatesStatisticsDto.builder()
                    .errorMessage("Error loading VAT rates from external source")
                    .build();
        }
        val vatRatesDto = vatRatesDtoOptional.get();
        Collections.sort(vatRatesDto.getVatRates(), vatRateComparator);

        val vatRates = vatRatesDto.getVatRates();
        val lowestVatRateCountry = vatRates.size() > 0 ? vatRates.get(1).getCountry() : null;
        val secondLowestVatRateCountry = vatRates.size() > 1 ? vatRates.get(2).getCountry() : null;
        val highestVatRateCountry = vatRates.size() > 0 ? vatRates.get(vatRates.size() - 1).getCountry() : null;
        val secondHighestVatRateCountry = vatRates.size() > 1 ? vatRates.get(vatRates.size() - 2).getCountry() : null;

        return VatRatesStatisticsDto.builder()
                .lowestVatRateCountry(lowestVatRateCountry)
                .secondLowestVatRateCountry(secondLowestVatRateCountry)
                .highestVatRateCountry(highestVatRateCountry)
                .secondHighestVatRateCountry(secondHighestVatRateCountry)
                .build();
    }
}
