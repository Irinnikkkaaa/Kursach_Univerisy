package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;

public class Group {
    private int number_of_group;
    private int number_of_students;
    @ManyToOne
    @JoinColumn(name = "fk_specialty_id")
    private Specialty specialty;
}

