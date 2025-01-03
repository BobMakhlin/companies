package com.makhlin.analyticsservice.listeners;

import com.makhlin.analyticsservice.service.CompanyService;
import com.makhlin.common.events.CompanyChanged;
import com.makhlin.common.exception.ItemNotFoundException;
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
        if (event.isNew()) {
            companyService.addCompany(event);
            return;
        }
        try {
            companyService.updateCompany(event);
        } catch (ItemNotFoundException itemNotFoundException) {
            // If version != 0, a company must exist!
            // Potentially, we could receive CompanyDeleted event before receiving CompanyChanged event,
            // so we shouldn't create a company in this case.
            log.error("Cannot update a company: company not found, companyId = {}", event.companyId());
        } catch (VersionConflictException versionConflictException) {
            // Skip outdated events.
            log.warn("Cannot update a company: version conflict, companyId = {}", event.companyId());
        }
    }
}
