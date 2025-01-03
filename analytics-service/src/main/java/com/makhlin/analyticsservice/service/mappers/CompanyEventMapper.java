package com.makhlin.analyticsservice.service.mappers;

import com.makhlin.analyticsservice.domain.CompanyEntity;
import com.makhlin.common.events.CompanyChanged;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE
)
public abstract class CompanyEventMapper {
    @Mapping(target = "id", source = "companyId")
    @Mapping(target = "modifiedDate", source = "modifiedAt")
    @Mapping(target = "companyAddresses", source = "addresses")
    public abstract CompanyEntity companyChangedToCompanyEntity(CompanyChanged event);
}
