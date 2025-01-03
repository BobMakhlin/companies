package com.makhlin.companyservice.service;

import java.util.UUID;

public interface CompanyEventPublisher {
    void publishCompanyDeletedEvent(UUID companyId);

    void publishCompanyChangedEvent(UUID companyId);
}
