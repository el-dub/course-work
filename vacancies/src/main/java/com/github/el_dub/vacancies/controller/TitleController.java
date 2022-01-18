package com.github.el_dub.vacancies.controller;

import com.github.el_dub.vacancies.dto.TitleDto;
import com.github.el_dub.vacancies.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/levels")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class TitleController {

    private final TitleService titleService;

    @GetMapping
    public List<TitleDto> getLevels() {
        return titleService.getLevels();
    }
}
