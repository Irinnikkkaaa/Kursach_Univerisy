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
@Table(name = "groups")
public class Group {
    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private Set<Student> studentSet = new LinkedHashSet<Student>();
    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private Set<Passes> passesSet = new LinkedHashSet<Passes>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number_of_group;
    @NonNull
    private int number_of_students;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_specialty_id")
    private Specialty specialty;
}