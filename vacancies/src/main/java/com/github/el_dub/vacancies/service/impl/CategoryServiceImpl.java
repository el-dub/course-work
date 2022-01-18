package com.github.el_dub.vacancies.service.impl;

import com.github.el_dub.vacancies.dto.CategoryDto;
import com.github.el_dub.vacancies.dto.mapper.CategoryMapper;
import com.github.el_dub.vacancies.repository.CategoryRepository;
import com.github.el_dub.vacancies.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryMapper.toDtoList(categoryRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name")));
    }
}
