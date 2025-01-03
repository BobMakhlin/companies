package com.makhlin.companyservice.service;

import com.makhlin.common.events.CompanyDeleted;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CompanyEventPublisherKafka implements CompanyEventPublisher {
    private final KafkaTemplate<String, CompanyDeleted> companyDeletedKafkaTemplate;

    @Override
    public void publishCompanyDeletedEvent(UUID companyId) {
        companyDeletedKafkaTemplate.send("company-deleted", new CompanyDeleted(companyId));
    }

    @Override
    public void publishCompanyChangedEvent(UUID companyId) {

    }
}
