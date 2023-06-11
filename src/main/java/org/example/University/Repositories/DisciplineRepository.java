package org.example.University.Repositories;

import org.example.University.Models.Discipline;
import org.example.University.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    Iterable<Discipline> findByTeacher(Teacher teacher);
}
