package com.makhlin.analyticsservice.service;

import com.makhlin.analyticsservice.domain.CompanyNameHistoryEntity;
import com.makhlin.analyticsservice.repository.CompanyAddressCustomRepository;
import com.makhlin.analyticsservice.repository.CompanyJpaRepository;
import com.makhlin.analyticsservice.repository.CompanyNameHistoryCustomRepository;
import com.makhlin.analyticsservice.service.mappers.AnalyticsMapper;
import com.makhlin.analyticsservice.swagger.api.AnalyticsApiDelegate;
import com.makhlin.analyticsservice.swagger.model.CompanyAddressStatistics;
import com.makhlin.analyticsservice.swagger.model.CompanyCurrentStatus;
import com.makhlin.analyticsservice.swagger.model.CompanyName;
import com.makhlin.analyticsservice.swagger.model.CompanyPreviousNames;
import com.makhlin.common.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnalyticsApiDelegateImpl implements AnalyticsApiDelegate {
    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyNameHistoryCustomRepository companyNameHistoryCustomRepository;
    private final AnalyticsMapper analyticsMapper;
    private final CompanyAddressCustomRepository companyAddressCustomRepository;

    @Override
    public ResponseEntity<CompanyCurrentStatus> getCompanyStatus(UUID companyId) {
        log.info("Get company status, companyId = {}", companyId);
        var companyEntity = companyJpaRepository.findById(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        var companyCurrentStatus = new CompanyCurrentStatus()
                .companyId(companyEntity.getId())
                .status(analyticsMapper.statusToCompanyStatus(companyEntity.getStatus()))
                .isDeleted(companyEntity.getDeleted());
        return new ResponseEntity<>(companyCurrentStatus, OK);
    }

    @Override
    public ResponseEntity<CompanyName> getCompanyName(UUID companyId) {
        log.info("Get company name, companyId = {}", companyId);
        var companyNameEntity = companyNameHistoryCustomRepository.findCurrentName(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        var companyName = new CompanyName()
                .companyId(companyId)
                .currentName(companyNameEntity.getName());
        return new ResponseEntity<>(companyName, OK);
    }

    @Override
    public ResponseEntity<CompanyPreviousNames> getCompanyPreviousNames(UUID companyId) {
        log.info("Get company previous names, companyId = {}", companyId);
        if (!companyJpaRepository.existsById(companyId)) {
            throw new ItemNotFoundException(companyId);
        }
        var previousNameEntities = companyNameHistoryCustomRepository.findPreviousNames(companyId);
        var companyPreviousNames = new CompanyPreviousNames()
                .companyId(companyId)
                .previousNames(previousNameEntities.stream().map(CompanyNameHistoryEntity::getName).toList());
        return new ResponseEntity<>(companyPreviousNames, OK);
    }

    @Override
    public ResponseEntity<CompanyAddressStatistics> getCompanyAddresses(UUID companyId) {
        log.info("Get company addresses, companyId = {}", companyId);
        if (!companyJpaRepository.existsById(companyId)) {
            throw new ItemNotFoundException(companyId);
        }
        var map = companyAddressCustomRepository.countByCategory(companyId);
        var companyAddressStatistics = new CompanyAddressStatistics()
                .companyId(companyId)
                .addressStatistics(map);
        return new ResponseEntity<>(companyAddressStatistics, OK);
    }
}
