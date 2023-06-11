package org.example.University.Controllers;

import org.example.University.Models.Discipline;
import org.example.University.Models.Faculty;
import org.example.University.Repositories.DisciplineRepository;
import org.example.University.Repositories.FacultyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("discipline")
public class DisciplineController {
    private final DisciplineRepository disciplineRepository;
    private final FacultyRepository facultyRepository;

    public DisciplineController(DisciplineRepository disciplineRepository, FacultyRepository facultyRepository) {
        this.disciplineRepository = disciplineRepository;
        this.facultyRepository = facultyRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        Iterable<Faculty> faculties = facultyRepository.findAll();

        model.addAttribute("disciplines", disciplines);
        model.addAttribute("faculties", faculties);
        return "discipline";
    }
    @PostMapping("add")
    public String add(@RequestParam String name,
                      @RequestParam String difficulty_level,
                      @RequestParam Faculty faculty) {
        Discipline discipline = new Discipline(name, difficulty_level, faculty);
        disciplineRepository.save(discipline);
        return "redirect:/discipline";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow();
        disciplineRepository.delete(discipline);
        return "redirect:/discipline";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow();
        Iterable<Faculty> faculties = facultyRepository.findAll();

        model.addAttribute("discipline", discipline);
        model.addAttribute("faculties", faculties);
        return "discipline-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name,
                       @RequestParam String difficulty_level,
                       @RequestParam Faculty faculty) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow();
        discipline.setName(name);
        discipline.setDifficulty_level(difficulty_level);
        discipline.setFaculty(faculty);
        disciplineRepository.save(discipline);
        return "redirect:/discipline";
    }
}
