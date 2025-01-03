package com.makhlin.companyservice.service;

import com.makhlin.common.events.CompanyChanged;
import com.makhlin.common.events.CompanyDeleted;
import com.makhlin.common.exception.ItemNotFoundException;
import com.makhlin.companyservice.repositories.CompanyJpaRepository;
import com.makhlin.companyservice.service.mappers.CompanyEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CompanyEventPublisherKafka implements CompanyEventPublisher {
    private static final int NEW_COMPANY_VERSION = 0;
    private final KafkaTemplate<String, CompanyDeleted> companyDeletedKafkaTemplate;
    private final KafkaTemplate<String, CompanyChanged> companyChangedKafkaTemplate;
    private final CompanyEventMapper companyEventMapper;
    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public void publishCompanyDeletedEvent(UUID companyId) {
        companyDeletedKafkaTemplate.send("company-deleted", new CompanyDeleted(companyId));
    }

    @Override
    public void publishCompanyUpdatedEvent(UUID companyId) {
        var companyChanged = buildCompanyChangedEvent(companyId, false);
        companyChangedKafkaTemplate.send("company-changed", companyChanged);
    }

    @Override
    public void publishCompanyCreatedEvent(UUID companyId) {
        var companyChanged = buildCompanyChangedEvent(companyId, true);
        companyChangedKafkaTemplate.send("company-changed", companyChanged);
    }

    private CompanyChanged buildCompanyChangedEvent(UUID companyId, boolean isNew) {
        var companyEntity = companyJpaRepository.findById(companyId)
                .orElseThrow(() -> new ItemNotFoundException(companyId));
        // So that we are sure that event will always have version=0 for new companies.
        var version = isNew ? NEW_COMPANY_VERSION : companyEntity.getVersion();
        return companyEventMapper.companyEntityToCompanyChanged(companyEntity, companyId, version);
    }
}
