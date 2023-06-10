package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;


public class Works {
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "fk_department_id")
    private Department department;
    private int number_of_working_hours;
}

