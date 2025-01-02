package com.makhlin.companyservice.repositories;

import com.makhlin.companyservice.domain.CompanyAddressEntity;
import com.makhlin.companyservice.domain.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAddressJpaRepository extends JpaRepository<CompanyAddressEntity, Long> {
    void deleteByCompany(CompanyEntity company);
}
