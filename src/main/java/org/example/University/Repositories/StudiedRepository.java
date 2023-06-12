package org.example.University.Repositories;

import org.example.University.Models.Specialty;
import org.example.University.Models.Studied;
import org.example.University.Models.TypeOfReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiedRepository extends JpaRepository<Studied, Long> {
    Iterable<Studied> findBySpecialty(Specialty specialty);
    Iterable<Studied> findBySpecialtyAndTypeOfReport(Specialty specialty, TypeOfReport typeOfReport);
 }
