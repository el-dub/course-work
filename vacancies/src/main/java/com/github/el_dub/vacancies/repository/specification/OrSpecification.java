package com.github.el_dub.vacancies.repository.specification;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class OrSpecification extends CompositeSpecification {

    private final ISpecification first;
    private final ISpecification second;

    public OrSpecification(ISpecification first, ISpecification second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Predicate getPredicate() {
        return ExpressionUtils.anyOf(first.getPredicate(), second.getPredicate());
    }
}
