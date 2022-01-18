package com.github.el_dub.vacancies.repository;

import com.github.el_dub.vacancies.dto.ItemStatistics;
import com.github.el_dub.vacancies.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface StatisticsRepository extends JpaRepository<Vacancy, UUID>, QueryFactoryVacancyRepository {

    @Query("SELECT new com.github.el_dub.vacancies.dto.ItemStatistics" +
            " (c.name, COUNT(v)) FROM Vacancy v" +
            " LEFT JOIN v.category c" +
            " WHERE v.placementTime >= :dateFrom" +
            " AND v.placementTime <= :dateTo" +
            " AND v.level.id IN :levelIds" +
            " AND EXISTS(SELECT l FROM v.locations l WHERE l.id IN :locationIds)" +
            " GROUP BY c" +
            " ORDER BY COUNT(v) DESC")
    List<ItemStatistics> getCategoriesStatistics1(@Param("dateFrom") LocalDateTime dateFrom,
                                                 @Param("dateTo") LocalDateTime dateTo,
                                                  @Param("levelIds") List<Integer> levelIds,
                                                  @Param("locationIds") List<Integer> locationIds);
    @Query("SELECT new com.github.el_dub.vacancies.dto.ItemStatistics" +
            " (c.name, COUNT(v)) FROM Vacancy v" +
            " LEFT JOIN v.company c" +
            " WHERE v.placementTime >= :dateFrom" +
            " AND v.placementTime <= :dateTo" +
            " AND v.category.id IN :categoryIds" +
            " AND v.level.id IN :levelIds" +
            " AND EXISTS(SELECT l FROM v.locations l WHERE l.id IN :locationIds)" +
            " GROUP BY c" +
            " ORDER BY COUNT(v) DESC")
    List<ItemStatistics> getCompaniesStatistics1(@Param("dateFrom") LocalDateTime dateFrom,
                                                  @Param("dateTo") LocalDateTime dateTo,
                                                  @Param("categoryIds") List<Integer> categoryIds,
                                                  @Param("levelIds") List<Integer> levelIds,
                                                  @Param("locationIds") List<Integer> locationIds);

    @Query("select category.name, count(vacancy.id)\n" +
            "from Vacancy vacancy\n" +
            "  left join Category category\n" +
            "where" +
//            " vacancy.company.id in ?1" +
//            " and vacancy.level.id in ?2" +
//            " and exists (select 1\n" +
//            "from vacancy.locations as vacancy_locations_0\n" +
//            "where vacancy_locations_0.id in ?3)" +
//            " and" +
            " vacancy.placementTime >= ?1" +
            " and vacancy.placementTime <= ?2\n" +
            "group by category\n" +
            "order by count(vacancy.id) desc")
    List<ItemStatistics> myQuery(
//            List<UUID> companyIds,
//                                 List<Integer> levelIds,
//                                 List<Integer> locationIds,
                                 @Param("dateFrom") LocalDateTime dateFrom,
                                 @Param("dateTo") LocalDateTime dateTo
    );
}
