package com.makhlin.analyticsservice.service;

import com.makhlin.analyticsservice.domain.CompanyEntity;
import com.makhlin.analyticsservice.domain.CompanyNameHistoryEntity;
import com.makhlin.analyticsservice.repository.CompanyJpaRepository;
import com.makhlin.analyticsservice.repository.CompanyNameHistoryCustomRepository;
import com.makhlin.analyticsservice.service.mappers.CompanyEventMapper;
import com.makhlin.common.events.CompanyChanged;
import com.makhlin.common.exception.VersionConflictException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
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
    private final EntityManager em;

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
    public void addOrUpdate(CompanyChanged event) {
        companyJpaRepository.findById(event.companyId())
                .ifPresentOrElse(companyEntity -> this.update(event, companyEntity), () -> this.add(event));
    }

    private void add(CompanyChanged event) {
        var companyEntity = new CompanyEntity();
        companyEventMapper.companyChangedToCompanyEntity(event, companyEntity);
        companyJpaRepository.saveAndFlush(companyEntity);
        companyNameHistoryCustomRepository.addName(companyEntity, event.name(), event.modifiedAt());
    }

    private void update(CompanyChanged event, CompanyEntity companyEntity) {
        if (event.version() < companyEntity.getVersion()) {
            throw new VersionConflictException();
        }
        companyEventMapper.companyChangedToCompanyEntity(event, companyEntity);
        companyJpaRepository.saveAndFlush(companyEntity);

        companyNameHistoryCustomRepository.findCurrentName(companyEntity.getId())
                .ifPresentOrElse(currentName -> {
                    if (isNameChanged(currentName, event)) {
                        companyNameHistoryCustomRepository.addName(companyEntity, event.name(), event.modifiedAt());
                    }
                }, () -> log.error("Could not find the current name for the company during update, companyId = {}", companyEntity.getId()));

    }

    private boolean isNameChanged(CompanyNameHistoryEntity currentName, CompanyChanged event) {
        return !currentName.getName().equals(event.name());
    }
}
