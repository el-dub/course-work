package com.github.el_dub.vacancies.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CompanyDto {
    private UUID id;
    private String name;
}
