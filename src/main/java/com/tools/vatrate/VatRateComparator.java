package com.tools.vatrate;

import com.dto.vatrate.VatRate;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class VatRateComparator implements Comparator<VatRate> {

    public int compare(VatRate vatRate1, VatRate vatRate2) {
        Integer standardRate1;
        try {
            standardRate1 = Integer.parseInt(vatRate1.getStandardRate());
        } catch (NumberFormatException e){
            standardRate1 = 0;
        }
        Integer standardRate2;
        try {
            standardRate2 = Integer.parseInt(vatRate2.getStandardRate());
        } catch (NumberFormatException e) {
            standardRate2 = 0;
        }
        return standardRate1 - standardRate2;
    }
}
