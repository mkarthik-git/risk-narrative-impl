package com.riskapi.risknarrativeimplementation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Address {
    @JsonProperty("locality")
    private String locality;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("premises")
    private String premises;

    @JsonProperty("address_line_1")
    private String addressLine1;

    @JsonProperty("country")
    private String country;

    public Address() {
    }

    public Address(String locality, String postalCode, String premises, String addressLine1, String country) {
        this.locality = locality;
        this.postalCode = postalCode;
        this.premises = premises;
        this.addressLine1 = addressLine1;
        this.country = country;
    }
}