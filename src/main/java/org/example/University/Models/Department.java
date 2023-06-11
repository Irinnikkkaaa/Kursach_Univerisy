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
public class Department {
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private Set<Works> worksSet = new LinkedHashSet<Works>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String type;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_faculty_id")
    private Faculty faculty;

}
