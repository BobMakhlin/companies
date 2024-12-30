package com.makhlin.companyservice.service;

import com.makhlin.companyservice.domain.AddressCategoryEntity;
import com.makhlin.companyservice.domain.IndustryEntity;
import com.makhlin.companyservice.repositories.AddressCategoryJpaRepository;
import com.makhlin.companyservice.repositories.IndustryJpaRepository;
import com.makhlin.companyservice.swagger.api.ConfigurationApiDelegate;
import com.makhlin.companyservice.swagger.model.BusinessConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigurationApiDelegateImpl implements ConfigurationApiDelegate {
    private final IndustryJpaRepository industryJpaRepository;
    private final AddressCategoryJpaRepository addressCategoryJpaRepository;

    @Override
    public ResponseEntity<BusinessConfiguration> getConfiguration() {
        log.info("Get configuration");
        var industries = industryJpaRepository.findAll().stream().map(IndustryEntity::getName).toList();
        var addressCategories = addressCategoryJpaRepository.findAll().stream().map(AddressCategoryEntity::getName).toList();
        return new ResponseEntity<>(new BusinessConfiguration()
                .industries(industries)
                .addressCategories(addressCategories), OK);
    }
}
