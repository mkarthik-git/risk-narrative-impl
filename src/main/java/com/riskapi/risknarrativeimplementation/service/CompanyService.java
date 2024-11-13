package com.riskapi.risknarrativeimplementation.service;

import com.riskapi.risknarrativeimplementation.entity.CompanyEntity;
import com.riskapi.risknarrativeimplementation.model.Company;
import com.riskapi.risknarrativeimplementation.model.CompanyLookupRequest;
import com.riskapi.risknarrativeimplementation.model.CompanyResponse;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyResponse searchCompanies(CompanyLookupRequest request, String apiKey, boolean onlyActive);
    Optional<List<Company>> getCompanyByNameAndCompanyNumber(String companyName, String companyNumber);
    Optional<List<Company>> getCompanyByCompanyNumber(String companyNumber);
    CompanyEntity saveCompany(CompanyEntity companyEntity);
}