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
public class Specialty {
    @OneToMany(mappedBy = "specialty", cascade = CascadeType.REMOVE)
    private Set<Studied> studiedSet = new LinkedHashSet<Studied>();
    @OneToMany(mappedBy = "specialty", cascade = CascadeType.REMOVE)
    private Set<Group> groupSet = new LinkedHashSet<Group>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    @NonNull
    private String name;
    @NonNull
    private int number_of_graduates;
}