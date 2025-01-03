package com.makhlin.analyticsservice.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "company_names")
public class CompanyNameHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(name = "modified_at", nullable = false)
    private Instant modifiedAt;
}
