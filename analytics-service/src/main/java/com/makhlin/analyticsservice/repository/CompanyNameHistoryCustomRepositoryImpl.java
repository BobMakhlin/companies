package com.makhlin.analyticsservice.repository;

import com.makhlin.analyticsservice.domain.CompanyEntity;
import com.makhlin.analyticsservice.domain.CompanyEntity_;
import com.makhlin.analyticsservice.domain.CompanyNameHistoryEntity;
import com.makhlin.analyticsservice.domain.CompanyNameHistoryEntity_;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class CompanyNameHistoryCustomRepositoryImpl implements CompanyNameHistoryCustomRepository {
    private final EntityManager em;

    @Override
    public Optional<CompanyNameHistoryEntity> findCurrentName(UUID companyId) {
        var cq = findAllNamesOrderByDateDesc(companyId);
        cq.fetch(1);
        var names = em.createQuery(cq).getResultList();
        return names.isEmpty() ? Optional.empty() : Optional.of(names.get(0));
    }

    @Override
    public List<CompanyNameHistoryEntity> findPreviousNames(UUID companyId) {
        var cq = findAllNamesOrderByDateDesc(companyId);
        var names = em.createQuery(cq).getResultList();
        // The first name is the current name of the company.
        return names.subList(1, names.size());
    }

    @Override
    public void addName(CompanyEntity companyEntity, String name, Instant modifiedAt) {
        var nameEntity = new CompanyNameHistoryEntity();
        nameEntity.setCompany(companyEntity);
        nameEntity.setName(name);
        nameEntity.setModifiedAt(modifiedAt);
        em.persist(nameEntity);
        em.flush();
    }

    private JpaCriteriaQuery<CompanyNameHistoryEntity> findAllNamesOrderByDateDesc(UUID companyId) {
        var cb = (HibernateCriteriaBuilder) em.getCriteriaBuilder();
        var cq = cb.createQuery(CompanyNameHistoryEntity.class);
        var root = cq.from(CompanyNameHistoryEntity.class);

        cq.select(root);
        cq.where(cb.equal(root.get(CompanyNameHistoryEntity_.COMPANY).get(CompanyEntity_.ID), companyId));
        cq.orderBy(cb.desc(root.get(CompanyNameHistoryEntity_.MODIFIED_AT)));

        return cq;
    }
}
