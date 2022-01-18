package com.github.el_dub.vacancies.service.impl;

import com.github.el_dub.vacancies.dto.TitleDto;
import com.github.el_dub.vacancies.dto.mapper.LevelMapper;
import com.github.el_dub.vacancies.repository.TitleRepository;
import com.github.el_dub.vacancies.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleRepository levelRepository;

    private final LevelMapper levelMapper;

    @Override
    public List<TitleDto> getLevels() {
        return levelMapper.toDtoList(
                levelRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }
}
