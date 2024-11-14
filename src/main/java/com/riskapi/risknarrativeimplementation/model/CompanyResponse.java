package com.riskapi.risknarrativeimplementation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CompanyResponse {
    private int totalResults;
    private List<Company> items;
}
