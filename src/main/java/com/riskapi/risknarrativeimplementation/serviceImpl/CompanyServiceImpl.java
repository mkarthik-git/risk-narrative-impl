package com.riskapi.risknarrativeimplementation.serviceImpl;

import com.riskapi.risknarrativeimplementation.entity.CompanyEntity;
import com.riskapi.risknarrativeimplementation.model.Company;
import com.riskapi.risknarrativeimplementation.model.CompanyLookupRequest;
import com.riskapi.risknarrativeimplementation.model.CompanyResponse;
import com.riskapi.risknarrativeimplementation.repository.CompanyRepository;
import com.riskapi.risknarrativeimplementation.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final RestTemplate restTemplate;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(RestTemplate restTemplate, CompanyRepository companyRepository) {
        this.restTemplate = restTemplate;
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse searchCompanies(CompanyLookupRequest request, String apiKey, boolean onlyActive) {
        String url = "https://api.truproxy.com/company/search";

        // Build the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build the request entity with headers and body
        HttpEntity<CompanyLookupRequest> entity = new HttpEntity<>(request, headers);

        // Make the API call
        ResponseEntity<CompanyResponse> response = restTemplate.postForEntity(url, entity, CompanyResponse.class);

        // Process the response
        CompanyResponse companyResponse = response.getBody();
        if (onlyActive && companyResponse != null) {
            companyResponse.setItems(companyResponse.getItems().stream()
                    .filter(item -> "active".equalsIgnoreCase(item.getCompanyStatus()))
                    .collect(Collectors.toList()));
        }
        return companyResponse;
    }

    @Override
    public Optional<List<Company>> getCompanyByNameAndCompanyNumber(String companyName, String companyNumber) {
        return companyRepository.findByNameAndNumber(companyName, companyNumber)
                .map(entities -> entities.stream()
                        .map(this::convertToModel)
                        .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Company>> getCompanyByCompanyNumber(String companyNumber) {
        return companyRepository.findByNumber(companyNumber)
                .map(entities -> entities.stream()
                        .map(this::convertToModel)
                        .collect(Collectors.toList()));
    }

    private Company convertToModel(CompanyEntity entity) {
        Company company = new Company();
        company.setCompanyName(entity.getCompanyName());
        company.setCompanyNumber(entity.getCompanyNumber());
        return company;
    }

    @Override
    public CompanyEntity saveCompany(CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity);
    }
}