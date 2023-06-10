package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Estimated {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private int estimation;
    @NonNull
    private String payment;
    @NonNull
    private int attempt_number;
    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_teacher_id")
    private Teacher teacher;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_studentIdNumber_id")
    private Student student_id_number;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_typeOfReport_id")
    private TypeOfReport typeOfReport;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_discipline_id")
    private Discipline discipline;
}
