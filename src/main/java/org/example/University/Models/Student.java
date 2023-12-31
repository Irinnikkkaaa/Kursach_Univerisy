package org.example.University.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.*;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {
    @OneToMany(mappedBy = "student_id_number", cascade = CascadeType.REMOVE)
    private Set<Estimated> estimatedSet = new LinkedHashSet<Estimated>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long student_id_number;
    @NonNull
    private String status;
    @NonNull
    private String fullname;
    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date_of_birth;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_group_id")
    private Group group;
}