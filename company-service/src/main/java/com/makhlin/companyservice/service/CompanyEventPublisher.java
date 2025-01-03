package com.makhlin.companyservice.service;

import java.util.UUID;

public interface CompanyEventPublisher {
    void publishCompanyDeletedEvent(UUID companyId);

    void publishCompanyUpdatedEvent(UUID companyId);

    void publishCompanyCreatedEvent(UUID companyId);
}
