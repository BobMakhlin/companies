package com.makhlin.companyservice.service;

import com.makhlin.companyservice.domain.CompanyStatus;
import com.makhlin.companyservice.repositories.CompanyJpaRepository;
import com.makhlin.companyservice.service.exception.ItemNotFoundException;
import com.makhlin.companyservice.service.mappers.CompanyMapper;
import com.makhlin.companyservice.swagger.api.CompaniesApiDelegate;
import com.makhlin.companyservice.swagger.model.Company;
import com.makhlin.companyservice.swagger.model.UpdateCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class CompaniesApiDelegateImpl implements CompaniesApiDelegate {
    private final CompanyMapper companyMapper;
    private final CompanyJpaRepository companyJpaRepository;

    @Transactional
    @Override
    public ResponseEntity<Company> addCompany(UpdateCompany body) {
        log.info("Add company");

        var companyEntity = companyMapper.updateCompanyToCompanyEntity(body);
        companyEntity.setStatus(CompanyStatus.ACTIVE);
        var savedEntity = companyJpaRepository.saveAndFlush(companyEntity);
        var company = companyMapper.companyEntityToCompany(savedEntity);

        return new ResponseEntity<>(company, OK);
    }

    @Override
    public ResponseEntity<Company> getCompany(UUID companyId) {
        log.info("Get company, companyId = {}", companyId);

        var companyEntity = companyJpaRepository.findById(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        var company = companyMapper.companyEntityToCompany(companyEntity);

        return new ResponseEntity<>(company, OK);
    }
}
