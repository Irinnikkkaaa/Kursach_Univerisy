package org.example.University.Controllers;

import org.example.University.Models.Department;
import org.example.University.Models.Faculty;
import org.example.University.Repositories.DepartmentRepository;
import org.example.University.Repositories.FacultyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    public DepartmentController(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Department> departments = departmentRepository.findAll();
        Iterable<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("faculties", faculties);
        return "department";
    }
    @PostMapping("add")
    public String add(@RequestParam String name,
                      @RequestParam String type,
                      @RequestParam Faculty faculty) {
        Department department = new Department(name, type, faculty);
        departmentRepository.save(department);
        return "redirect:/department";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        departmentRepository.delete(department);
        return "redirect:/department";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Department department = departmentRepository.findById(id).orElseThrow();
        Iterable<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("department", department);
        model.addAttribute("faculties", faculties);
        return "department-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name,
                       @RequestParam String type,
                       @RequestParam Faculty faculty) {
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setName(name);
        department.setType(type);
        department.setFaculty(faculty);
        return "redirect:/department";
    }
}