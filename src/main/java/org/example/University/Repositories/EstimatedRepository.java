package org.example.University.Repositories;

import org.example.University.Models.Estimated;
import org.example.University.Models.TypeOfReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstimatedRepository extends JpaRepository<Estimated, Long> {
    Iterable<Estimated> findByPayment(String payment);
    Iterable<Estimated> findByTypeOfReportAndEstimationGreaterThanEqual(Optional<TypeOfReport> typeOfReport, int estimation);
}