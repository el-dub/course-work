package com.github.el_dub.vacancies.service;

import com.github.el_dub.vacancies.dto.CategoryFilter;
import com.github.el_dub.vacancies.dto.CompanyFilter;
import com.github.el_dub.vacancies.dto.ItemStatistics;
import com.github.el_dub.vacancies.dto.Statistics;

import java.util.List;

public interface StatisticsService {

    Statistics getStatisticsByCategories(CategoryFilter filter);

    Statistics getStatisticsByCompanies(CompanyFilter filter);
}
