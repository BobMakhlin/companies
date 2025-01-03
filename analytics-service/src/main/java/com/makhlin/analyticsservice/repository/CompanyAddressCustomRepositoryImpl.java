package com.makhlin.analyticsservice.repository;

import com.makhlin.analyticsservice.domain.CompanyAddressEntity;
import com.makhlin.analyticsservice.domain.CompanyAddressEntity_;
import com.makhlin.analyticsservice.domain.CompanyEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class CompanyAddressCustomRepositoryImpl implements CompanyAddressCustomRepository {
    private static final String CATEGORY_ALIAS = "category";
    private static final String COUNT_ALIAS = "address_count";
    private final EntityManager em;

    @Override
    public Map<String, Long> countByCategory(UUID companyId) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(Tuple.class);
        var root = cq.from(CompanyAddressEntity.class);

        cq.multiselect(root.get(CompanyAddressEntity_.CATEGORY).alias(CATEGORY_ALIAS),
                cb.count(root).alias(COUNT_ALIAS));
        cq.where(cb.equal(root.get(CompanyAddressEntity_.COMPANY).get(CompanyEntity_.ID), companyId));
        cq.groupBy(root.get(CompanyAddressEntity_.CATEGORY));

        var tuples = em.createQuery(cq).getResultList();
        var map = new HashMap<String, Long>();
        for (var tuple : tuples) {
            var category = tuple.get(CATEGORY_ALIAS, String.class);
            var count = tuple.get(COUNT_ALIAS, Long.class);
            map.put(category, count);
        }
        return map;
    }
}
