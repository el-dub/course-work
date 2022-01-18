package com.github.el_dub.vacancies.service;

import com.github.el_dub.vacancies.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> getCompanies();
}
