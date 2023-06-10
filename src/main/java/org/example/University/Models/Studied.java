package org.example.University.Models;

import jakarta.persistence.*;
import lombok.*;

public class Studied {
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_specialty_id")
    private Specialty specialty;
    @ManyToOne
    @JoinColumn(name = "fk_discipline_id")
    private Discipline discipline;
    @ManyToOne
    @JoinColumn(name = "fk_typeOfReport_id")
    private TypeOfReport typeOfReport;
    private int number_of_hours;
}

