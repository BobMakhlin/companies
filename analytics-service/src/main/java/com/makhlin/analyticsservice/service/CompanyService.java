package com.makhlin.analyticsservice.service;

import java.util.UUID;

public interface CompanyService {
    /**
     * Mark company as deleted.
     *
     * @return False if company not found, True is company is successfully deleted.
     */
    boolean deleteCompany(UUID companyId);
}
