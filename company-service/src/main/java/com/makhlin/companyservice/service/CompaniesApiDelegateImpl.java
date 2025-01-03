package com.makhlin.companyservice.service;

import com.makhlin.common.exception.ItemNotFoundException;
import com.makhlin.common.exception.VersionConflictException;
import com.makhlin.companyservice.domain.CompanyEntity;
import com.makhlin.companyservice.domain.CompanyEntity_;
import com.makhlin.companyservice.domain.CompanyStatus;
import com.makhlin.companyservice.repositories.CompanyAddressJpaRepository;
import com.makhlin.companyservice.repositories.CompanyJpaRepository;
import com.makhlin.companyservice.service.mappers.CompanyMapper;
import com.makhlin.companyservice.swagger.api.CompaniesApiDelegate;
import com.makhlin.companyservice.swagger.model.Company;
import com.makhlin.companyservice.swagger.model.UpdateCompany;
import com.makhlin.companyservice.swagger.model.UpdateCompanyStatus;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleStateException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.makhlin.common.utils.IfMatchUtil.isEqualVersion;
import static com.makhlin.common.utils.PaginationUtil.getPaginationResponseHeaders;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class CompaniesApiDelegateImpl implements CompaniesApiDelegate {
    private final CompanyMapper companyMapper;
    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyAddressJpaRepository companyAddressJpaRepository;
    private final CompanyEventPublisher companyEventPublisher;

    @Transactional
    @Override
    public ResponseEntity<Company> addCompany(UpdateCompany body) {
        log.info("Add company");

        var companyEntity = companyMapper.updateCompanyToCompanyEntity(body);
        companyEntity.setStatus(CompanyStatus.ACTIVE);
        var savedEntity = companyJpaRepository.saveAndFlush(companyEntity);
        var company = companyMapper.companyEntityToCompany(savedEntity);
        companyEventPublisher.publishCompanyCreatedEvent(savedEntity.getId());

        return new ResponseEntity<>(company, OK);
    }

    @Transactional
    @Override
    public ResponseEntity<Company> updateCompany(String ifMatch, UUID companyId, UpdateCompany body) {
        log.info("Update company, ifMatch = {}, companyId = {}", ifMatch, companyId);

        var companyEntity = companyJpaRepository.findById(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        if (!isEqualVersion(ifMatch, companyEntity.getVersion())) {
            throw new VersionConflictException();
        }
        companyMapper.updateCompanyToCompanyEntity(body, companyEntity);
        companyEntity.triggerVersionIncrement();

        CompanyEntity savedEntity;
        try {
            savedEntity = companyJpaRepository.saveAndFlush(companyEntity);
        } catch (DataAccessException ex) {
            throw handleDataAccessException(ex);
        }
        var company = companyMapper.companyEntityToCompany(savedEntity);
        companyEventPublisher.publishCompanyUpdatedEvent(companyId);

        return ResponseEntity
                .ok()
                .eTag(String.valueOf(savedEntity.getVersion()))
                .body(company);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> changeCompanyStatus(String ifMatch, UUID companyId, UpdateCompanyStatus body) {
        log.info("Change company status, ifMatch = {}, companyId = {}", ifMatch, companyId);

        var companyStatus = companyMapper.statusToCompanyStatus(body.getStatus());
        var companyEntity = companyJpaRepository.findById(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        if (!isEqualVersion(ifMatch, companyEntity.getVersion())) {
            throw new VersionConflictException();
        }
        companyEntity.setStatus(companyStatus);
        companyEntity.triggerVersionIncrement();

        CompanyEntity savedEntity;
        try {
            savedEntity = companyJpaRepository.saveAndFlush(companyEntity);
        } catch (DataAccessException ex) {
            throw handleDataAccessException(ex);
        }
        companyEventPublisher.publishCompanyUpdatedEvent(companyId);

        return ResponseEntity
                .ok()
                .eTag(String.valueOf(savedEntity.getVersion()))
                .build();
    }

    @Override
    public ResponseEntity<Company> getCompany(UUID companyId) {
        log.info("Get company, companyId = {}", companyId);

        var companyEntity = companyJpaRepository.findById(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        var company = companyMapper.companyEntityToCompany(companyEntity);

        return new ResponseEntity<>(company, OK);
    }

    @Override
    public ResponseEntity<List<Company>> getCompanies(Integer pageNumber, Integer pageSize) {
        log.info("Get companies, pageNumber = {}, pageSize = {}", pageNumber, pageSize);

        // By default, sorting by name.
        var pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, CompanyEntity_.NAME));
        var page = companyJpaRepository.findAll(pageable);
        var companies = companyMapper.companyEntitiesToCompanies(page.getContent());
        var headers = getPaginationResponseHeaders(page.getTotalElements(), page.getTotalPages());

        return new ResponseEntity<>(companies, headers, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> deleteCompany(UUID companyId) {
        log.info("Delete company, companyId = {}", companyId);

        var companyEntity = companyJpaRepository.findById(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        companyAddressJpaRepository.deleteByCompany(companyEntity);
        companyJpaRepository.delete(companyEntity);
        companyEventPublisher.publishCompanyDeletedEvent(companyId);

        return new ResponseEntity<>(OK);
    }

    private RuntimeException handleDataAccessException(DataAccessException ex) {
        if (ex.getCause() instanceof StaleStateException) {
            throw new VersionConflictException();
        } else {
            throw ex;
        }
    }
}
