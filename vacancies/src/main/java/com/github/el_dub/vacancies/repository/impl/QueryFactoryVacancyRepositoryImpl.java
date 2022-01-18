package com.github.el_dub.vacancies.repository.impl;

import com.github.el_dub.vacancies.dto.ItemStatistics;
import com.github.el_dub.vacancies.model.QCategory;
import com.github.el_dub.vacancies.model.QCompany;
import com.github.el_dub.vacancies.model.QLocation;
import com.github.el_dub.vacancies.model.QVacancy;
import com.github.el_dub.vacancies.repository.QueryFactoryVacancyRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryFactoryVacancyRepositoryImpl implements QueryFactoryVacancyRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemStatistics> getCategoriesStatistics(Predicate predicate) {
        QVacancy qVacancy = QVacancy.vacancy;
        QCategory qCategory = QCategory.category;
        QCompany qCompany = QCompany.company;
        QLocation qLocation = QLocation.location;
        JPQLQuery<ItemStatistics> query = queryFactory
                .from(qVacancy)
                .leftJoin(qCategory)
                .leftJoin(qCompany)
//                .leftJoin(qLocation)
                .where(predicate)
                .groupBy(qCategory)
                .orderBy(qVacancy.count().desc())
                .select(Projections.constructor(ItemStatistics.class, qCategory.name, qVacancy.count()));
        return query.fetch();
    }

    @Override
    public List<ItemStatistics> getCompaniesStatistics(Predicate predicate) {
        QVacancy qVacancy = QVacancy.vacancy;
        QCompany qCompany = QCompany.company;
        JPQLQuery<ItemStatistics> query = queryFactory
                .from(qVacancy)
                .leftJoin(qCompany)
                .where(predicate)
                .groupBy(qCompany)
                .orderBy(qVacancy.id.count().desc())
                .select(Projections.constructor(ItemStatistics.class, qCompany.name, qVacancy.id.count()));
        return query.fetch();
    }

//    private OrderSpecifier<?> getSortedColumn(Sort.Order order, String fieldName) {
//        Path<Object> fieldPath = Expressions.path(Object.class, QDiscount.discount, fieldName);
//        return new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
//                fieldPath);
//    }
}
