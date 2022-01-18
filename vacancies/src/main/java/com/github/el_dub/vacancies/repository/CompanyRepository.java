package com.github.el_dub.vacancies.repository;

import com.github.el_dub.vacancies.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByName(String name);

    boolean existsByName(String name);
}
