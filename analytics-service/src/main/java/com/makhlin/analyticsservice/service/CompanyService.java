package com.makhlin.analyticsservice.service;

import com.makhlin.common.events.CompanyChanged;

import java.util.UUID;

public interface CompanyService {
    /**
     * Mark company as deleted.
     *
     * @return False if company not found, True is company is successfully deleted.
     */
    boolean deleteCompany(UUID companyId);

    /**
     * Update company.
     *
     * @param event Represents the desired state of the company.
     * @throws com.makhlin.common.exception.VersionConflictException When the given version is outdated.
     */
    void addOrUpdate(CompanyChanged event);
}
