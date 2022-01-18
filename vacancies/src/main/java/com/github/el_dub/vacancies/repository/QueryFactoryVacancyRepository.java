package com.github.el_dub.vacancies.repository;

import com.github.el_dub.vacancies.dto.ItemStatistics;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryFactoryVacancyRepository {

    List<ItemStatistics> getCategoriesStatistics(Predicate predicate);

    List<ItemStatistics> getCompaniesStatistics(Predicate predicate);
}
