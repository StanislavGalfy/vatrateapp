package com.controller.vatrate;

import com.dto.vatrate.VatRatesStatisticsDto;
import com.service.vatrate.VatRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vatrate")
@RequiredArgsConstructor
public class VatRateController {

    private final VatRateService vatRateService;

    @GetMapping("/statistics")
    public VatRatesStatisticsDto getVatRatesStatistics() {
        return vatRateService.getVatRateStatistic();
    }
}
