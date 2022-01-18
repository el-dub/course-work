package com.github.el_dub.vacancies.repository.specification;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NotSpecification extends CompositeSpecification {

    private final ISpecification wrapped;

    public NotSpecification(ISpecification wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Predicate getPredicate() {
        return wrapped.getPredicate().not();
    }
}
