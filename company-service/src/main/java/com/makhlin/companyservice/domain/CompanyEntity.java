package com.makhlin.companyservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class CompanyEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "fax")
    private String fax;
    @ManyToOne
    @JoinColumn(name = "industry_id", nullable = false)
    private IndustryEntity industry;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CompanyStatus status;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdDate;
    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private Instant modifiedDate;
    @Version
    @Column(name = "version", nullable = false)
    @ColumnDefault("0")
    private int version;

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

    public void triggerVersionIncrement() {
        this.setModifiedDate(Instant.now());
    }

    private void addAddress(CompanyAddressEntity companyAddress) {
        companyAddress.setCompany(this);
        this.companyAddressEntities.add(companyAddress);
    }

    private void removeAddress(CompanyAddressEntity companyAddress) {
        this.companyAddressEntities.remove(companyAddress);
    }
}