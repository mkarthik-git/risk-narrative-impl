package com.riskapi.risknarrativeimplementation.repository;

import com.riskapi.risknarrativeimplementation.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<List<CompanyEntity>> findByNameAndNumber(String companyName, String companyNumber);
    Optional<List<CompanyEntity>> findByNumber(String companyNumber);
}