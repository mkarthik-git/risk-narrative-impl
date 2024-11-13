package com.riskapi.risknarrativeimplementation.model;

import lombok.Data;

import java.util.List;

@Data
public class Company {
    private String companyNumber;
    private String companyName;
    private String companyType;
    private String title;
    private String companyStatus;
    private String dateOfCreation;
    private Address address;
    private List<Officer> officers;
}