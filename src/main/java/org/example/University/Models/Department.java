package org.example.University.Models;


import jakarta.persistence.*;
import lombok.*;

public class Department {
    private int id;
    private String name;
    private String type;
    @ManyToOne
    @JoinColumn(name = "fk_faculty_id")
    private Faculty faculty;

}
