package com.github.el_dub.vacancies.dto.mapper;

import com.github.el_dub.vacancies.dto.CompanyDto;
import com.github.el_dub.vacancies.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto toDto(Company company);

    List<CompanyDto> toDtoList(List<Company> companies);
}
