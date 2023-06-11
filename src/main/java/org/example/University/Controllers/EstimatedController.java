package org.example.University.Controllers;

import org.example.University.Models.*;
import org.example.University.Repositories.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("estimated")
public class EstimatedController {
    private final EstimatedRepository estimatedRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final TypeOfReportRepository typeOfReportRepository;
    private final DisciplineRepository disciplineRepository;

    public EstimatedController(EstimatedRepository estimatedRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, TypeOfReportRepository typeOfReportRepository, DisciplineRepository disciplineRepository) {
        this.estimatedRepository = estimatedRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.typeOfReportRepository = typeOfReportRepository;
        this.disciplineRepository = disciplineRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Estimated> estimateds = estimatedRepository.findAll();
        Iterable<Teacher> teachers = teacherRepository.findAll();
        Iterable<Student> students = studentRepository.findAll();
        Iterable<TypeOfReport> typeOfReports = typeOfReportRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();

        model.addAttribute("estimateds", estimateds);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        model.addAttribute("typeOfReports", typeOfReports);
        model.addAttribute("disciplines", disciplines);

        return "estimated";
    }
    @PostMapping("add")
    public String add(@RequestParam int estimation,
                      @RequestParam String payment,
                      @RequestParam int attempt_number,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                      @RequestParam Teacher teacher,
                      @RequestParam Student student_id_number,
                      @RequestParam TypeOfReport typeOfReport,
                      @RequestParam Discipline discipline) {
        Estimated estimated = new Estimated(estimation, payment, attempt_number, date, teacher, student_id_number, typeOfReport, discipline);
        estimatedRepository.save(estimated);
        return "redirect:/estimated";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Estimated estimated = estimatedRepository.findById(id).orElseThrow();
        estimatedRepository.delete(estimated);
        return "redirect:/estimated";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Estimated estimated = estimatedRepository.findById(id).orElseThrow();
        Iterable<Teacher> teachers = teacherRepository.findAll();
        Iterable<Student> students = studentRepository.findAll();
        Iterable<TypeOfReport> typeOfReports = typeOfReportRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();

        model.addAttribute("estimated", estimated);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        model.addAttribute("typeOfReports", typeOfReports);
        model.addAttribute("disciplines", disciplines);
        return "estimated-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam int estimation,
                       @RequestParam String payment,
                       @RequestParam int attempt_number,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                       @RequestParam Teacher teacher,
                       @RequestParam Student student_id_number,
                       @RequestParam TypeOfReport typeOfReport,
                       @RequestParam Discipline discipline) {
        Estimated estimated = estimatedRepository.findById(id).orElseThrow();
        estimated.setEstimation(estimation);
        estimated.setPayment(payment);
        estimated.setAttempt_number(attempt_number);
        estimated.setDate(date);
        estimated.setTeacher(teacher);
        estimated.setStudent_id_number(student_id_number);
        estimated.setTypeOfReport(typeOfReport);
        estimated.setDiscipline(discipline);
        estimatedRepository.save(estimated);
        return "redirect:/estimated";
    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String payment,
                         @RequestParam(required = false) Integer estimation,
                         Model model) {
        Iterable<Estimated> estimateds = estimatedRepository.findAll();
        Iterable<Teacher> teachers = teacherRepository.findAll();
        Iterable<Student> students = studentRepository.findAll();
        Iterable<TypeOfReport> typeOfReports = typeOfReportRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();

        if(payment != null && !payment.isEmpty()) {
            estimateds = estimatedRepository.findByPayment(payment);
        }
        if(estimation != null) {
            Optional<TypeOfReport> typeOfReport = typeOfReportRepository.findByName("Зачет");
            estimateds = estimatedRepository.findByTypeOfReportAndEstimationGreaterThanEqual(typeOfReport, estimation);
        }

        model.addAttribute("estimateds", estimateds);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        model.addAttribute("typeOfReports", typeOfReports);
        model.addAttribute("disciplines", disciplines);

        return "estimated";
    }
}
