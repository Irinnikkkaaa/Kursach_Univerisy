package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Studied {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private int number_of_hours;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_specialty_id")
    private Specialty specialty;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_discipline_id")
    private Discipline discipline;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_typeOfReport_id")
    private TypeOfReport typeOfReport;
}