package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Works {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private int number_of_working_hours;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_teacher_id")
    private Teacher teacher;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_department_id")
    private Department department;
}

