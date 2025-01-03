package com.makhlin.analyticsservice.repository;

import com.makhlin.analyticsservice.domain.CompanyNameHistoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyNameHistoryCustomRepository {
    Optional<CompanyNameHistoryEntity> findCurrentName(UUID companyId);

    List<CompanyNameHistoryEntity> findPreviousNames(UUID companyId);
}
