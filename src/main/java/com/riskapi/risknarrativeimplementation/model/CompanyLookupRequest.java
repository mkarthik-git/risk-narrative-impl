package com.riskapi.risknarrativeimplementation.model;

import lombok.Data;

@Data
public class CompanyLookupRequest {
    private String companyName;
    private String companyNumber;
}