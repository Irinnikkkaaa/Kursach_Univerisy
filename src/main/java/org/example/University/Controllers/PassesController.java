package org.example.University.Controllers;

import org.example.University.Models.Discipline;
import org.example.University.Models.Group;
import org.example.University.Models.Passes;
import org.example.University.Repositories.DisciplineRepository;
import org.example.University.Repositories.GroupRepository;
import org.example.University.Repositories.PassesRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("passes")
public class PassesController {
    private final PassesRepository passesRepository;
    private final GroupRepository groupRepository;
    private final DisciplineRepository disciplineRepository;
    public PassesController(PassesRepository passesRepository, GroupRepository groupRepository, DisciplineRepository disciplineRepository) {
        this.passesRepository = passesRepository;
        this.groupRepository = groupRepository;
        this.disciplineRepository = disciplineRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Passes> passes = passesRepository.findAll();
        Iterable<Group> groups = groupRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();

        model.addAttribute("passes", passes);
        model.addAttribute("groups", groups);
        model.addAttribute("disciplines", disciplines);
        return "passes";
    }
    @PostMapping("add")
    public String add(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                      @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time,
                      @RequestParam Group group,
                      @RequestParam Discipline discipline) {
        Passes passes = new Passes(date, time, group, discipline);
        passesRepository.save(passes);
        return "redirect:/passes";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Passes passes = passesRepository.findById(id).orElseThrow();
        passesRepository.delete(passes);
        return "redirect:/passes";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Passes passes = passesRepository.findById(id).orElseThrow();
        Iterable<Group> groups = groupRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();

        model.addAttribute("passes", passes);
        model.addAttribute("groups", groups);
        model.addAttribute("disciplines", disciplines);
        return "passes-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                       @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time,
                       @RequestParam Group group,
                       @RequestParam Discipline discipline) {
        Passes passes = passesRepository.findById(id).orElseThrow();
        passes.setDate(date);
        passes.setTime(time);
        passes.setGroup(group);
        passes.setDiscipline(discipline);
        passesRepository.save(passes);
        return "redirect:/passes";
    }
}
