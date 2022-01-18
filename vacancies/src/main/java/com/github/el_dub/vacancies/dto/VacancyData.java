package com.github.el_dub.vacancies.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VacancyData {
    String name;
    String placementDate;
    List<String> locations;
    String description;
    String link;
    String category;
    String company;
}
