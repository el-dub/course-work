package com.github.el_dub.vacancies.repository.specification;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.domain.Specification;

public abstract class CompositeSpecification implements ISpecification {

    @Override
    public abstract Predicate getPredicate();

    @Override
    public ISpecification and(ISpecification other) {
        return new AndSpecification(this, other);
    }

    @Override
    public ISpecification or(ISpecification other) {
        return new OrSpecification(this, other);
    }

    @Override
    public ISpecification not() {
        return new NotSpecification(this);
    }
}
