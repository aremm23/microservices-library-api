package com.artsem.api.crudservice.filter;

import com.artsem.api.crudservice.model.UserDetails;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public record UserFilter(String emailContains, LocalDateTime createdAtGte, LocalDateTime createdAtLte, Integer ageGt) {
    public Specification<UserDetails> toSpecification() {
        return Specification.where(emailContainsSpec())
                .and(createdAtGteSpec())
                .and(createdAtLteSpec())
                .and(ageGtSpec());
    }

    private Specification<UserDetails> emailContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(emailContains)
                ? cb.like(cb.lower(root.get("email")), "%" + emailContains.toLowerCase() + "%")
                : null);
    }

    private Specification<UserDetails> createdAtGteSpec() {
        return ((root, query, cb) -> createdAtGte != null
                ? cb.greaterThanOrEqualTo(root.get("createdAt"), createdAtGte)
                : null);
    }

    private Specification<UserDetails> createdAtLteSpec() {
        return ((root, query, cb) -> createdAtLte != null
                ? cb.lessThanOrEqualTo(root.get("createdAt"), createdAtLte)
                : null);
    }

    private Specification<UserDetails> ageGtSpec() {
        return ((root, query, cb) -> ageGt != null
                ? cb.greaterThan(root.get("age"), ageGt)
                : null);
    }
}