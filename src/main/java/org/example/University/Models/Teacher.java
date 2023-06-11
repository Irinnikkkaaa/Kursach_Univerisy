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
public class Teacher {
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private Set<Discipline> disciplineSet = new LinkedHashSet<Discipline>();
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private Set<Works> worksSet = new LinkedHashSet<Works>();
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private Set<Estimated> estimatedSet = new LinkedHashSet<Estimated>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String fullname;
    @NonNull
    private String position;
}