package com.github.el_dub.vacancies.repository;

import com.github.el_dub.vacancies.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    Optional<Location> findByName(String name);

    boolean existsByName(String name);
}
