package com.makhlin.companyservice.repositories;

import com.makhlin.companyservice.domain.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndustryJpaRepository extends JpaRepository<IndustryEntity, Integer> {
    Optional<IndustryEntity> findByName(String name);
}
