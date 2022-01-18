package com.github.el_dub.vacancies.service.impl;

import com.github.el_dub.vacancies.dto.CompanyDto;
import com.github.el_dub.vacancies.dto.mapper.CompanyMapper;
import com.github.el_dub.vacancies.repository.CompanyRepository;
import com.github.el_dub.vacancies.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getCompanies() {
        return companyMapper.toDtoList(companyRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name")));
    }
}
