package org.example.University.Repositories;

import org.example.University.Models.Passes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassesRepository extends JpaRepository<Passes, Long> {
}
