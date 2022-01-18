package com.github.el_dub.vacancies.repository;

import com.github.el_dub.vacancies.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {

    boolean existsByLinkAndCategoryName(String link, String categoryName);
}
