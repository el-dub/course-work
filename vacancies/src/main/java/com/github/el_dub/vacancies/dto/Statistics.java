package com.github.el_dub.vacancies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private long sum;
    private List<ItemStatistics> items;
}
