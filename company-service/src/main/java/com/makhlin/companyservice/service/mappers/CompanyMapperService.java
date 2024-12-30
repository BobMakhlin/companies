package com.makhlin.companyservice.service.mappers;

import com.makhlin.companyservice.domain.IndustryEntity;
import com.makhlin.companyservice.repositories.IndustryJpaRepository;
import com.makhlin.companyservice.service.exception.BadParamsException;
import com.makhlin.companyservice.service.exception.ErrorTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CompanyMapperService {
    private final IndustryJpaRepository industryJpaRepository;

    public IndustryEntity getIndustryEntity(String industryName) {
        return industryJpaRepository.findByName(industryName)
                .orElseThrow(() -> new BadParamsException(ErrorTypeEnum.INVALID_INDUSTRY));
    }

    public String getIndustryName(IndustryEntity industryEntity) {
        return industryEntity.getName();
    }
}
