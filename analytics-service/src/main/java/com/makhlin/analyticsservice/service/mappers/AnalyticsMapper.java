package com.makhlin.analyticsservice.service.mappers;

import com.makhlin.analyticsservice.domain.CompanyStatus;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE
)
public abstract class AnalyticsMapper {
    public abstract com.makhlin.analyticsservice.swagger.model.CompanyStatus statusToCompanyStatus(CompanyStatus status);
}
