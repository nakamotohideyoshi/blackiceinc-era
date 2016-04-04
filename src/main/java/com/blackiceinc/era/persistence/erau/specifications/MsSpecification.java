package com.blackiceinc.era.persistence.erau.specifications;


import com.blackiceinc.era.persistence.erau.model.Customer;
import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class MsSpecification implements Specification<MeasurementSensitivity> {
    private SpecSearchCriteria criteria;

    public MsSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(final Root<MeasurementSensitivity> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.<String>get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.<String>get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case INNER_JOIN:
                Join<MeasurementSensitivity, Customer> customer = root.join("customer");
                return builder.equal(customer.get(criteria.getKey()), criteria.getValue());
            default:
                return null;
        }
    }
}
