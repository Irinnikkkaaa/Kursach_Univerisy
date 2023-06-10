package org.example.University.Repositories;

import org.example.University.Models.Studied;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiedRepository extends JpaRepository<Studied, Long> {
}
