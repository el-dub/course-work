package com.github.el_dub.vacancies.repository.specification;

import com.querydsl.core.types.Predicate;

import java.util.function.Function;

public class Specification extends CompositeSpecification {

    private Predicate predicate;

    public <T> Specification(T object, Function<T, Predicate> function) {
        if (object != null) {
            this.predicate = function.apply(object);
        }
    }

    @Override
    public Predicate getPredicate() {
        return predicate;
    }
}
