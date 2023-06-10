package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;

public class Discipline {
    private int id;
    private String name;
    private String difficulty_level;
    @ManyToOne
    @JoinColumn(name = "fk_faculty_id")
    private Faculty faculty;
}
