package com.dto.vatrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VatRate {

    @JsonProperty("_comment")
    private String comment;

    @JsonProperty("iso_duplicate")
    private String isoDuplicate;

    @JsonProperty("iso_duplicate_of")
    private String isoDuplicateOf;

    @JsonProperty("country")
    private String country;

    @JsonProperty("standard_rate")
    private String standardRate;

    @JsonProperty("reduced_rate")
    private String reducedRate;

    @JsonProperty("reduced_rate_alt")
    private String reducedRateAlt;

    @JsonProperty("super_reduced_rate")
    private String superReducedRate;

    @JsonProperty("parking_rate")
    private String parkingRate;
}
