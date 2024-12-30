package com.makhlin.companyservice.repositories;

import com.makhlin.companyservice.domain.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryJpaRepository extends JpaRepository<IndustryEntity, Integer> {
}
