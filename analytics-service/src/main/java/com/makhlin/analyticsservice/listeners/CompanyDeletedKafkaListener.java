package com.makhlin.analyticsservice.listeners;

import com.makhlin.analyticsservice.service.CompanyService;
import com.makhlin.common.events.CompanyDeleted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CompanyDeletedKafkaListener {
    private final CompanyService companyService;

    @KafkaListener(topics = "company-deleted")
    public void listen(CompanyDeleted event) {
        log.info("Company deleted event received, companyId = {}", event.companyId());
        if (!companyService.deleteCompany(event.companyId())) {
            // Ensure consumer idempotency.
            log.warn("Cannot delete a company: company not found, companyId = {}", event.companyId());
        }
    }
}
