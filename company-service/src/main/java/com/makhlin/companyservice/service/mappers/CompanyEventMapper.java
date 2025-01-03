package com.makhlin.companyservice.service.mappers;

import com.makhlin.common.events.CompanyChanged;
import com.makhlin.companyservice.domain.CompanyAddressEntity;
import com.makhlin.companyservice.domain.CompanyEntity;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        uses = {CompanyMapperService.class})
public abstract class CompanyEventMapper {
    @Mapping(target = "modifiedAt", source = "modifiedDate")
    @Mapping(target = "addresses", source = "companyAddressEntities")
    @Mapping(target = "companyId", expression = "java(companyId)")
    @Mapping(target = "version", expression = "java(version)")
    public abstract CompanyChanged companyEntityToCompanyChanged(CompanyEntity companyEntity, @Context UUID companyId, @Context int version);

    public abstract CompanyChanged.Address companyAddressEntityToAddress(CompanyAddressEntity companyAddressEntity);
}
