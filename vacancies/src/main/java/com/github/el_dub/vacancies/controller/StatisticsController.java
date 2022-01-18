package com.github.el_dub.vacancies.controller;

import com.github.el_dub.vacancies.dto.CategoryFilter;
import com.github.el_dub.vacancies.dto.CompanyFilter;
import com.github.el_dub.vacancies.dto.ItemStatistics;
import com.github.el_dub.vacancies.dto.Statistics;
import com.github.el_dub.vacancies.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping("/categories")
    public Statistics getStatisticsByCategory(@RequestBody CategoryFilter filter) {
        return statisticsService.getStatisticsByCategories(filter);
    }

    @PostMapping("/companies")
    public Statistics getStatisticsByCompany(@RequestBody CompanyFilter filter) {
        return statisticsService.getStatisticsByCompanies(filter);
    }
}
