package com.makhlin.companyservice.service.mappers;

import com.makhlin.companyservice.domain.CompanyAddressEntity;
import com.makhlin.companyservice.domain.CompanyEntity;
import com.makhlin.companyservice.domain.CompanyStatus;
import com.makhlin.companyservice.swagger.model.Address;
import com.makhlin.companyservice.swagger.model.Company;
import com.makhlin.companyservice.swagger.model.Contacts;
import com.makhlin.companyservice.swagger.model.UpdateCompany;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        uses = {CompanyMapperService.class}
)
public abstract class CompanyMapper {
    @Mapping(target = ".", source = "contacts")
    @Mapping(target = "companyAddresses", source = "addresses")
    public abstract CompanyEntity updateCompanyToCompanyEntity(UpdateCompany updateCompany);

    @Mapping(target = ".", source = "contacts")
    @Mapping(target = "companyAddresses", source = "addresses")
    public abstract void updateCompanyToCompanyEntity(UpdateCompany updateCompany, @MappingTarget CompanyEntity companyEntity);

    @Mapping(target = "contacts", source = ".")
    @Mapping(target = "addresses", source = "companyAddressEntities")
    public abstract Company companyEntityToCompany(CompanyEntity companyEntity);

    public abstract List<Company> companyEntitiesToCompanies(List<CompanyEntity> companyEntities);

    public abstract Contacts companyEntityToContacts(CompanyEntity companyEntity);

    public abstract CompanyStatus statusToCompanyStatus(com.makhlin.companyservice.swagger.model.CompanyStatus companyStatus);

    public abstract Set<CompanyAddressEntity> addressesToCompanyAddressEntities(List<Address> addresses);

    public abstract List<Address> companyAddressEntitiesToAddresses(Set<CompanyAddressEntity> companyAddressEntities);
}
