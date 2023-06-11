package org.example.University.Controllers;

import org.example.University.Models.Department;
import org.example.University.Models.Teacher;
import org.example.University.Models.Works;
import org.example.University.Repositories.DepartmentRepository;
import org.example.University.Repositories.TeacherRepository;
import org.example.University.Repositories.WorksRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("works")
public class WorksController {
    private final WorksRepository worksRepository;
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public WorksController(WorksRepository worksRepository, TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.worksRepository = worksRepository;
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String all(Model model) {
        Iterable<Works> works = worksRepository.findAll();
        Iterable<Teacher> teachers = teacherRepository.findAll();
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("works", works);
        model.addAttribute("teachers", teachers);
        model.addAttribute("departments", departments);
        return "works";
    }

    @PostMapping("add")
    public String add(@RequestParam int number_of_working_hours,
                      @RequestParam Teacher teacher,
                      @RequestParam Department department) {
        Works works = new Works(number_of_working_hours, teacher, department);
        worksRepository.save(works);
        return "redirect:/works";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Works works = worksRepository.findById(id).orElseThrow();
        worksRepository.delete(works);
        return "redirect:/works";
    }

    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Works works = worksRepository.findById(id).orElseThrow();
        Iterable<Teacher> teachers = teacherRepository.findAll();
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("works", works);
        model.addAttribute("teachers", teachers);
        model.addAttribute("departments", departments);
        return "works-edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam int number_of_working_hours,
                       @RequestParam Teacher teacher,
                       @RequestParam Department department) {
        Works works = worksRepository.findById(id).orElseThrow();
        works.setNumber_of_working_hours(number_of_working_hours);
        works.setTeacher(teacher);
        works.setDepartment(department);
        worksRepository.save(works);
        return "redirect:/works";
    }
}