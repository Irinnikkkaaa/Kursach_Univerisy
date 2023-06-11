package org.example.University.Repositories;

import org.example.University.Models.TypeOfReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfReportRepository extends JpaRepository<TypeOfReport, Long> {
    Optional<TypeOfReport> findByName(String name);
}
