package com.makhlin.analyticsservice.listeners;

import com.makhlin.analyticsservice.service.CompanyService;
import com.makhlin.common.events.CompanyChanged;
import com.makhlin.common.exception.VersionConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CompanyChangedKafkaListener {
    private final CompanyService companyService;

    @KafkaListener(topics = "company-changed")
    public void listen(CompanyChanged event) {
        log.info("Company changed event received, companyId = {}, version = {}", event.companyId(), event.version());
        try {
            companyService.addOrUpdate(event);
        } catch (VersionConflictException versionConflictException) {
            // Skip outdated events.
            log.warn("Cannot update a company: version conflict, companyId = {}", event.companyId());
        }
    }
}
