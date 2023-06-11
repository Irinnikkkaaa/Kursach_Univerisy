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
public class TypeOfReport {
    @OneToMany(mappedBy = "typeOfReport", cascade = CascadeType.REMOVE)
    private Set<Studied> studiedSet = new LinkedHashSet<Studied>();
    @OneToMany(mappedBy = "typeOfReport", cascade = CascadeType.REMOVE)
    private Set<Estimated> estimatedSet = new LinkedHashSet<Estimated>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
}
