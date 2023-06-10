package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Passes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @NonNull
    @Column(columnDefinition = "TIME WITHOUT TIME ZONE")
    private LocalTime time;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_group_id")
    private Group group;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_discipline_id")
    private Discipline discipline;
}
