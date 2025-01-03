package com.makhlin.analyticsservice.repository;

import com.makhlin.analyticsservice.domain.CompanyEntity;
import com.makhlin.analyticsservice.domain.CompanyNameHistoryEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyNameHistoryCustomRepository {
    Optional<CompanyNameHistoryEntity> findCurrentName(UUID companyId);

    List<CompanyNameHistoryEntity> findPreviousNames(UUID companyId);

    void addName(CompanyEntity companyEntity, String name, Instant modifiedAt);
}
