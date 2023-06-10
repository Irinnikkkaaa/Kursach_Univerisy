package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String difficulty_level;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_faculty_id")
    private Faculty faculty;
}
