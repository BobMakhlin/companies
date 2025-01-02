package com.makhlin.companyservice.repositories;

import com.makhlin.companyservice.domain.AddressCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressCategoryJpaRepository extends JpaRepository<AddressCategoryEntity, Integer> {
    Optional<AddressCategoryEntity> findByName(String name);
}
