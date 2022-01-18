package com.github.el_dub.vacancies.service.impl;

import com.github.el_dub.vacancies.dto.CategoryFilter;
import com.github.el_dub.vacancies.dto.CompanyFilter;
import com.github.el_dub.vacancies.dto.ItemStatistics;
import com.github.el_dub.vacancies.dto.Statistics;
import com.github.el_dub.vacancies.model.QVacancy;
import com.github.el_dub.vacancies.repository.StatisticsRepository;
import com.github.el_dub.vacancies.repository.specification.Specification;
import com.github.el_dub.vacancies.service.StatisticsService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics getStatisticsByCategories(CategoryFilter filter) {
        List<ItemStatistics> items = statisticsRepository
                .getCategoriesStatistics1(filter.getDateFrom(), filter.getDateTo(), filter.getLevelIds(), filter.getLocationIds());
//        List<ItemStatistics> items = statisticsRepository
//                .getCategoriesStatistics(preparePredicateForStatisticsByCategories(filter));
//                .getCategoriesStatistics(filter.getDateFrom(), filter.getDateTo());
        return new Statistics(items.stream().mapToLong(ItemStatistics::getNumber).sum(), items);
    }

    @Override
    public Statistics getStatisticsByCompanies(CompanyFilter filter) {
        List<ItemStatistics> items = statisticsRepository
                .getCompaniesStatistics1(filter.getDateFrom(), filter.getDateTo(), filter.getCategoryIds(), filter.getLevelIds(), filter.getLocationIds());
//        List<ItemStatistics> items = statisticsRepository
//                .getCompaniesStatistics(preparePredicateForStatisticsByCompanies(filter));
        return new Statistics(items.stream().mapToLong(ItemStatistics::getNumber).sum(), items);
    }

    private Predicate preparePredicateForStatisticsByCategories(CategoryFilter filter) {
        return new Specification(filter.getLevelIds(), QVacancy.vacancy.level.id::in)
                .and(new Specification(filter.getLocationIds(), QVacancy.vacancy.locations.any().id::in))
                .and(new Specification(filter.getDateFrom(), QVacancy.vacancy.placementTime::goe))
                .and(new Specification(filter.getDateTo(), QVacancy.vacancy.placementTime::loe))
                .getPredicate();
    }

    private Predicate preparePredicateForStatisticsByCompanies(CompanyFilter filter) {
        return new Specification(filter.getCategoryIds(), QVacancy.vacancy.category.id::in)
                .and(new Specification(filter.getLevelIds(), QVacancy.vacancy.level.id::in))
                .and(new Specification(filter.getLocationIds(), QVacancy.vacancy.locations.any().id::in))
                .and(new Specification(filter.getDateFrom(), QVacancy.vacancy.placementTime::goe))
                .and(new Specification(filter.getDateTo(), QVacancy.vacancy.placementTime::loe))
                .getPredicate();
    }
}
