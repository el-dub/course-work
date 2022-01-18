package com.github.el_dub.vacancies.repository;

import com.github.el_dub.vacancies.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    boolean existsByName(String name);

    Optional<Title> findByName(String name);
}
