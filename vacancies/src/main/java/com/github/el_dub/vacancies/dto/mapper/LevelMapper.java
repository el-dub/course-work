package com.github.el_dub.vacancies.dto.mapper;

import com.github.el_dub.vacancies.dto.TitleDto;
import com.github.el_dub.vacancies.model.Title;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LevelMapper {

    TitleDto toDto(Title level);

    List<TitleDto> toDtoList(List<Title> levels);
}
