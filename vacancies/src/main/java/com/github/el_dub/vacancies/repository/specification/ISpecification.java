package com.github.el_dub.vacancies.repository.specification;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.domain.Specification;

public interface ISpecification {

    Predicate getPredicate();

    ISpecification and(ISpecification specification);

    ISpecification or(ISpecification specification);

    ISpecification not();
}
