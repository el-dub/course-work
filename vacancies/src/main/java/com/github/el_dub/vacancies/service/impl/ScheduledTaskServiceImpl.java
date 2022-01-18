package com.github.el_dub.vacancies.service.impl;

import com.github.el_dub.vacancies.service.ScheduledTaskService;
import com.github.el_dub.vacancies.service.ScrapingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTaskServiceImpl implements ScheduledTaskService {

    private final ScrapingService scrapingService;

    private static final long DAY_IN_MS = 86400000;
    private static final long TEN_SECONDS = 10000;
    private static final long TEN_THOUSANDS_SECONDS = 10000000;

    @Override
    @Scheduled(fixedRate = DAY_IN_MS, initialDelay = TEN_THOUSANDS_SECONDS)
    public void saveDataFromFirstService() {
        scrapingService.scrapeDouVacancies();
    }

    @Override
    @Scheduled(fixedRate = DAY_IN_MS, initialDelay = TEN_THOUSANDS_SECONDS)
    public void saveDataFromSecondService() {
        scrapingService.scrapeDjinniVacancies();
    }
}
