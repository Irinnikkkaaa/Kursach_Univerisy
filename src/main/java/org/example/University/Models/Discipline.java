package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Discipline {
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
    private Set<Studied> studiedSet = new LinkedHashSet<Studied>();
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
    private Set<Passes> passesSet = new LinkedHashSet<Passes>();
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
    private Set<Estimated> estimatedSet = new LinkedHashSet<Estimated>();
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
