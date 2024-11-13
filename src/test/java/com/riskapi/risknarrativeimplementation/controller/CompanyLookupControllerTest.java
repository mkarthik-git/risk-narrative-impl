package com.riskapi.risknarrativeimplementation.controller;

import com.riskapi.risknarrativeimplementation.entity.CompanyEntity;
import com.riskapi.risknarrativeimplementation.model.Company;
import com.riskapi.risknarrativeimplementation.model.CompanyLookupRequest;
import com.riskapi.risknarrativeimplementation.model.CompanyResponse;
import com.riskapi.risknarrativeimplementation.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CompanyLookupControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyLookupController companyLookupController;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testGetCompanySearch() {
        CompanyLookupRequest request = new CompanyLookupRequest();
        CompanyResponse response = new CompanyResponse();
        response.setItems(Arrays.asList(new Company(), new Company()));
        when(companyService.searchCompanies(any(), any(), anyBoolean())).thenReturn(response);

        ResponseEntity<List<Company>> result = companyLookupController.getCompanySearch(request, "api-key", true);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void testGetByCompanyNameAndNumber() {
        when(companyService.getCompanyByNameAndCompanyNumber("TestName", "12345"))
                .thenReturn(Optional.of(Arrays.asList(new Company(), new Company())));

        ResponseEntity<List<Company>> result = companyLookupController.getByCompanyNameAndNumber("TestName", "12345");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void testGetByCompanyNumber() {
        when(companyService.getCompanyByCompanyNumber("12345"))
                .thenReturn(Optional.of(Arrays.asList(new Company(), new Company())));

        ResponseEntity<List<Company>> result = companyLookupController.getByCompanyNumber("12345");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void testCreateCompanyDetails() {
        CompanyEntity companyEntity = new CompanyEntity();
        when(companyService.saveCompany(any())).thenReturn(companyEntity);

        ResponseEntity<CompanyEntity> result = companyLookupController.createCompanyDetails(companyEntity);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(companyEntity, result.getBody());
    }
}