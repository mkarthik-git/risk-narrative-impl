package com.riskapi.risknarrativeimplementation.model;

import lombok.Data;
import java.util.List;

@Data
public class CompanyResponse {
    private int totalResults;
    private List<Company> items;
}
