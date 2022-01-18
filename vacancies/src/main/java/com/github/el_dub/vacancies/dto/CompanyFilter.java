package com.github.el_dub.vacancies.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CompanyFilter {
    private static final LocalDateTime DEFAULT_DATE_FROM = LocalDateTime.parse("2000-01-01T00:00:00");
    private static final LocalDateTime DEFAULT_DATE_TO = LocalDateTime.parse("3000-01-01T00:00:00");

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTo;
    private List<Integer> categoryIds;
    private List<Integer> levelIds;
    private List<Integer> locationIds;
}
