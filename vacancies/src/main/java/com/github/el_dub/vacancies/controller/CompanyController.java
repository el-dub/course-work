package com.github.el_dub.vacancies.controller;

import com.github.el_dub.vacancies.dto.CompanyDto;
import com.github.el_dub.vacancies.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getCompanies() {
        return companyService.getCompanies();
    }
}
