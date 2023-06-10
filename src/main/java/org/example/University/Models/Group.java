package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Group {
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