package com.makhlin.companyservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

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
}