package org.example.University.Controllers;

import org.example.University.Models.Teacher;
import org.example.University.Repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("teacher")
public class TeacherController {
    private final TeacherRepository teacherRepository;
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Teacher> teachers = teacherRepository.findAll();

        model.addAttribute("teachers", teachers);
        return "teacher";
    }
    @PostMapping("add")
    public String add(@RequestParam String fullname,
                      @RequestParam String position) {
        Teacher teacher = new Teacher(fullname, position);
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        teacherRepository.delete(teacher);
        return "redirect:/teacher";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        model.addAttribute("teacher", teacher);
        return "teacher-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String fullname,
                       @RequestParam String position) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        teacher.setFullname(fullname);
        teacher.setPosition(position);
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }
}