package com.makhlin.companyservice.repositories;

import com.makhlin.companyservice.domain.AddressCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCategoryJpaRepository extends JpaRepository<AddressCategoryEntity, Integer> {
}
