package org.example.University.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
}