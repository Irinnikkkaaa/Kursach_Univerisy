package org.example.University.Controllers;

import org.example.University.Models.Group;
import org.example.University.Models.Student;
import org.example.University.Repositories.GroupRepository;
import org.example.University.Repositories.StudentRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentController(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        Iterable<Group> groups = groupRepository.findAll();

        model.addAttribute("students", students);
        model.addAttribute("groups", groups);
        return "student";
    }
    @PostMapping("add")
    public String add(@RequestParam String status,
                      @RequestParam String fullname,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date_of_birth,
                      @RequestParam Group group) {
        Student student = new Student(status, fullname, date_of_birth, group);
        studentRepository.save(student);
        return "redirect:/student";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
        return "redirect:/student";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Student student = studentRepository.findById(id).orElseThrow();
        Iterable<Group> groups = groupRepository.findAll();

        model.addAttribute("student", student);
        model.addAttribute("groups", groups);
        return "student-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String status,
                       @RequestParam String fullname,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date_of_birth,
                       @RequestParam Group group) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setStatus(status);
        student.setFullname(fullname);
        student.setDate_of_birth(date_of_birth);
        student.setGroup(group);
        studentRepository.save(student);
        return "redirect:/student";
    }
}