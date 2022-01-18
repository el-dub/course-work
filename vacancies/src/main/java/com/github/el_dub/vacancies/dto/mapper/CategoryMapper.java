package com.github.el_dub.vacancies.dto.mapper;

import com.github.el_dub.vacancies.dto.CategoryDto;
import com.github.el_dub.vacancies.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categories);
}
