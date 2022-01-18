package com.github.el_dub.vacancies.dto.mapper;

import com.github.el_dub.vacancies.dto.LocationDto;
import com.github.el_dub.vacancies.model.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toDto(Location location);

    List<LocationDto> toDtoList(List<Location> locations);
}
