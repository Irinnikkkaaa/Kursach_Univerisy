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
public class Faculty {
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    private Set<Department> departmentSet = new LinkedHashSet<Department>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
}