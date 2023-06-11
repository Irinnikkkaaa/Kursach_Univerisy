package org.example.University.Controllers;

import org.example.University.Models.Faculty;
import org.example.University.Repositories.FacultyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyRepository facultyRepository;

    public FacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("faculties", faculties);
        return "faculty";
    }
    @PostMapping("add")
    public String add(@RequestParam String name) {
        Faculty faculty = new Faculty(name);
        facultyRepository.save(faculty);
        return "redirect:/faculty";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow();
        facultyRepository.delete(faculty);
        return "redirect:/faculty";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow();
        model.addAttribute("faculty", faculty);
        return "faculty-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow();
        faculty.setName(name);
        facultyRepository.save(faculty);
        return "redirect:/faculty";
    }
}
