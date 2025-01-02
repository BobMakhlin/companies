package com.makhlin.companyservice.service.mappers;

import com.makhlin.common.exception.BadParamsException;
import com.makhlin.companyservice.domain.AddressCategoryEntity;
import com.makhlin.companyservice.domain.IndustryEntity;
import com.makhlin.companyservice.repositories.AddressCategoryJpaRepository;
import com.makhlin.companyservice.repositories.IndustryJpaRepository;
import com.makhlin.companyservice.service.error.ErrorTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CompanyMapperService {
    private final IndustryJpaRepository industryJpaRepository;
    private final AddressCategoryJpaRepository addressCategoryJpaRepository;

    public IndustryEntity getIndustryEntity(String industryName) {
        return industryJpaRepository.findByName(industryName)
                .orElseThrow(() -> new BadParamsException(ErrorTypeEnum.INVALID_INDUSTRY));
    }

    public String getIndustryName(IndustryEntity industryEntity) {
        return industryEntity.getName();
    }

    public AddressCategoryEntity getAddressCategoryEntity(String addressCategoryName) {
        return addressCategoryJpaRepository.findByName(addressCategoryName)
                .orElseThrow(() -> new BadParamsException(ErrorTypeEnum.INVALID_ADDRESS_CATEGORY));
    }

    public String getAddressCategoryName(AddressCategoryEntity addressCategoryEntity) {
        return addressCategoryEntity.getName();
    }
}
