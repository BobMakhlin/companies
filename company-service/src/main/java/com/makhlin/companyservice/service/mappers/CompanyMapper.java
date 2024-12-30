package com.makhlin.companyservice.service.mappers;

import com.makhlin.companyservice.domain.CompanyEntity;
import com.makhlin.companyservice.swagger.model.Company;
import com.makhlin.companyservice.swagger.model.Contacts;
import com.makhlin.companyservice.swagger.model.UpdateCompany;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        uses = {CompanyMapperService.class}
)
public abstract class CompanyMapper {
    @Mapping(target = ".", source = "contacts")
    public abstract CompanyEntity updateCompanyToCompanyEntity(UpdateCompany updateCompany);

    @Mapping(target = "contacts", source = ".")
    public abstract Company companyEntityToCompany(CompanyEntity companyEntity);

    public abstract Contacts companyEntityToContacts(CompanyEntity companyEntity);
}
