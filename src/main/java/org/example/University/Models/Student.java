package org.example.University.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

public class Student {
    private int student_id_number;
    private String status;
    private String fullname;
    private LocalDate date_of_birth;
    @ManyToOne
    @JoinColumn(name = "fk_group_id")
    private Group group;

}
