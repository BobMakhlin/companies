package com.makhlin.analyticsservice.service;

import com.makhlin.analyticsservice.repository.CompanyJpaRepository;
import com.makhlin.analyticsservice.repository.CompanyNameHistoryCustomRepository;
import com.makhlin.analyticsservice.service.mappers.CompanyEventMapper;
import com.makhlin.common.events.CompanyChanged;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyEventMapper companyEventMapper;
    private final CompanyNameHistoryCustomRepository companyNameHistoryCustomRepository;

    @Transactional
    @Override
    public boolean deleteCompany(UUID companyId) {
        var company = companyJpaRepository.findById(companyId);
        if (company.isEmpty()) {
            return false;
        }
        var entity = company.get();
        entity.setDeleted(true);
        companyJpaRepository.saveAndFlush(entity);
        return true;
    }

    @Transactional
    @Override
    public void addCompany(CompanyChanged event) {
        var companyEntity = companyEventMapper.companyChangedToCompanyEntity(event);
        companyJpaRepository.saveAndFlush(companyEntity);
        companyNameHistoryCustomRepository.addName(companyEntity, event.name(), event.modifiedAt());
    }

    @Transactional
    @Override
    public void updateCompany(CompanyChanged event) {

    }
}
