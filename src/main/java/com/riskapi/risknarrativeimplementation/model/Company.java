package com.riskapi.risknarrativeimplementation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Company {
    private String name;
    private String companyNumber;
    private String companyName;
    private String companyType;
    private String title;
    private String companyStatus;
    private String dateOfCreation;
    @JsonProperty("appointed_on")
    private String appointedOn;
    @JsonProperty("officer_role")
    private String officerRole;
    @JsonProperty("date_of_birth")
    private DateOfBirth dateOfBirth;
    private String occupation;
    private Address address;
    private Links links;
    private List<Officer> officers;
    @JsonProperty("country_of_residence")
    private String countryOfResidence;
    private String nationality;
    @JsonProperty("resigned_on")
    private String resignedOn;
}