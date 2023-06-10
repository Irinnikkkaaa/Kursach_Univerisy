package org.example.University.Repositories;

import org.example.University.Models.Works;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorksRepository extends JpaRepository<Works, Long> {
}
