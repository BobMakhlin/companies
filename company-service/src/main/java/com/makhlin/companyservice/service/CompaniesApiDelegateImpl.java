package com.makhlin.companyservice.service;

import com.makhlin.companyservice.repositories.CompanyJpaRepository;
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
        var savedEntity = companyJpaRepository.saveAndFlush(companyEntity);
        var company = companyMapper.companyEntityToCompany(savedEntity);

        return new ResponseEntity<>(company, OK);
    }

}
