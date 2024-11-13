package com.riskapi.risknarrativeimplementation.controller;

import com.riskapi.risknarrativeimplementation.entity.CompanyEntity;
import com.riskapi.risknarrativeimplementation.model.Company;
import com.riskapi.risknarrativeimplementation.model.CompanyLookupRequest;
import com.riskapi.risknarrativeimplementation.model.CompanyResponse;
import com.riskapi.risknarrativeimplementation.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies/v1")
public class CompanyLookupController {
    CompanyService companyService;

    @Autowired
    public CompanyLookupController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/lookup")
    public ResponseEntity<List<Company>> getCompanySearch(@RequestBody CompanyLookupRequest request,
                                                          @RequestHeader("x-api-key") String apiKey,
                                                          @RequestParam(required = false) boolean onlyActive) {

        CompanyResponse companyResponse = companyService.searchCompanies(request, apiKey, onlyActive);
        List<Company> companyList = companyResponse != null ? companyResponse.getItems() : new ArrayList<>();
        return ResponseEntity.ok(companyList);
    }

    @GetMapping("/lookup/{companyName}/{companyNumber}")
    public ResponseEntity<List<Company>> getByCompanyNameAndNumber(@PathVariable("companyName") String companyName,
                                                                   @PathVariable("companyNumber") String companyNumber) {
        Optional<List<Company>> companyData = companyService.getCompanyByNameAndCompanyNumber(companyName, companyNumber);
        return companyData.map(companies -> new ResponseEntity<>(companies, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lookup/{companyNumber}")
    public ResponseEntity<List<Company>> getByCompanyNumber(@PathVariable("companyNumber") String companyNumber) {
        Optional<List<Company>> companyData = companyService.getCompanyByCompanyNumber(companyNumber);
        return companyData.map(companies -> new ResponseEntity<>(companies, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/saveCompany")
    public ResponseEntity<CompanyEntity> createCompanyDetails(@RequestBody CompanyEntity companyEntity) {
        try {
            CompanyEntity companyData = companyService
                    .saveCompany(companyEntity);
            return new ResponseEntity<>(companyData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
