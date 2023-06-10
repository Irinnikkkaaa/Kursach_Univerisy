package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


public class Estimated {
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_studentIdNumber_id")
    private Student student_id_number;  //????
    @ManyToOne
    @JoinColumn(name = "fk_typeOfReport_id")
    private TypeOfReport typeOfReport;
    @ManyToOne
    @JoinColumn(name = "fk_discipline_id")
    private Discipline discipline;
    private int estimation;
    private String payment;
    private int attempt_number;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "fk_teacher_id")
    private Teacher teacher;


}
