package com.makhlin.analyticsservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CompanyEntity {
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CompanyStatus status;
    @Column(name = "modified_at", nullable = false)
    private Instant modifiedDate;
    @Column(name = "version", nullable = false)
    @ColumnDefault("0")
    private int version;
    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToMany(targetEntity = CompanyAddressEntity.class,
            cascade = ALL,
            fetch = FetchType.LAZY, mappedBy = "company", orphanRemoval = true)
    @BatchSize(size = 50)
    private Set<CompanyAddressEntity> companyAddressEntities = new HashSet<>();

    public void setCompanyAddresses(Set<CompanyAddressEntity> companyAddresses) {
        if (companyAddressEntities.isEmpty()) {
            companyAddresses.forEach(this::addAddress);
            return;
        }
        var addressesToRemove = CollectionUtils.subtract(companyAddressEntities, companyAddresses);
        addressesToRemove.forEach(this::removeAddress);
        var addressesToCreate = CollectionUtils.subtract(companyAddresses, companyAddressEntities);
        addressesToCreate.forEach(this::addAddress);
    }

    private void addAddress(CompanyAddressEntity companyAddress) {
        companyAddress.setCompany(this);
        this.companyAddressEntities.add(companyAddress);
    }

    private void removeAddress(CompanyAddressEntity companyAddress) {
        this.companyAddressEntities.remove(companyAddress);
    }
}