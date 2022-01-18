package com.github.el_dub.vacancies.controller;

import com.github.el_dub.vacancies.dto.LocationDto;
import com.github.el_dub.vacancies.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public List<LocationDto> getLocations() {
        return locationService.getLocations();
    }
}
