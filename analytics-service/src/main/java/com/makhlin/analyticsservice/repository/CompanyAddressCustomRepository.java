package com.makhlin.analyticsservice.repository;

import java.util.Map;
import java.util.UUID;

public interface CompanyAddressCustomRepository {
    Map<String, Long> countByCategory(UUID companyId);
}
