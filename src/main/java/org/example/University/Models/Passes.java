package org.example.University.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

public class Passes {
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "fk_discipline_id")
    private Discipline discipline;
    private LocalDate date;
    private LocalDateTime time;
}
