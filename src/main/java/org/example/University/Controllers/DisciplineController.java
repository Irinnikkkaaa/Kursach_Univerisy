package org.example.University.Controllers;

import org.example.University.Models.Discipline;
import org.example.University.Models.Teacher;
import org.example.University.Repositories.DisciplineRepository;
import org.example.University.Repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("discipline")
public class DisciplineController {
    private final DisciplineRepository disciplineRepository;
    private final TeacherRepository teacherRepository;

    public DisciplineController(DisciplineRepository disciplineRepository, TeacherRepository teacherRepository) {
        this.disciplineRepository = disciplineRepository;
        this.teacherRepository = teacherRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        Iterable<Teacher> teachers = teacherRepository.findAll();

        model.addAttribute("disciplines", disciplines);
        model.addAttribute("teachers", teachers);
        return "discipline";
    }
    @PostMapping("add")
    public String add(@RequestParam String name,
                      @RequestParam String difficulty_level,
                      @RequestParam Teacher teacher) {
        Discipline discipline = new Discipline(name, difficulty_level, teacher);
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
        Iterable<Teacher> teachers = teacherRepository.findAll();

        model.addAttribute("discipline", discipline);
        model.addAttribute("teachers", teachers);
        return "discipline-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name,
                       @RequestParam String difficulty_level,
                       @RequestParam Teacher teacher) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow();
        discipline.setName(name);
        discipline.setDifficulty_level(difficulty_level);
        discipline.setTeacher(teacher);
        disciplineRepository.save(discipline);
        return "redirect:/discipline";
    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) Teacher teacher,
                         Model model) {
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        Iterable<Teacher> teachers = teacherRepository.findAll();

        if(teacher != null) {
            disciplines = disciplineRepository.findByTeacher(teacher);
        }

        model.addAttribute("disciplines", disciplines);
        model.addAttribute("teachers", teachers);
        return "discipline";
    }
}
