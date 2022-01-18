package com.github.el_dub.vacancies.service.impl;

import com.github.el_dub.vacancies.dto.LocationDto;
import com.github.el_dub.vacancies.dto.mapper.LocationMapper;
import com.github.el_dub.vacancies.repository.LocationRepository;
import com.github.el_dub.vacancies.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    @Override
    public List<LocationDto> getLocations() {
        return locationMapper.toDtoList(locationRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name")));
    }
}
