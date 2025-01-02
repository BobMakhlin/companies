package com.makhlin.companyservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(
        name = "company_address",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_company_address",
                columnNames = {"company_id", "country", "city", "zip", "street", "category_id"}
        )
)
@Getter
@Setter
public class CompanyAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private AddressCategoryEntity category;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "zip", nullable = false)
    private String zip;
    @Column(name = "street", nullable = false)
    private String street;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompanyAddressEntity that = (CompanyAddressEntity) o;
        return Objects.equals(category, that.category) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(zip, that.zip) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, country, city, zip, street);
    }
}