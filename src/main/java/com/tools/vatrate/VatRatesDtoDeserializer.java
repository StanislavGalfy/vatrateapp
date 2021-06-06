package com.tools.vatrate;

import com.dto.vatrate.VatRatesDto;
import com.dto.vatrate.VatRate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;

import lombok.val;

public class VatRatesDtoDeserializer extends StdDeserializer<VatRatesDto> {

    public VatRatesDtoDeserializer() {
        this(null);
    }

    public VatRatesDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public VatRatesDto deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        val rootNode = (JsonNode) jp.getCodec().readTree(jp);
        val vatRatesNode = rootNode.get("rates");

        val vatRates = new ArrayList<VatRate>();
        val objectMapper = new ObjectMapper();
        for (val vatRateNode : vatRatesNode) {
            vatRates.add(objectMapper.treeToValue(vatRateNode, VatRate.class));
        }
        return VatRatesDto.builder()
                .vatRates(vatRates)
                .build();
    }
}
