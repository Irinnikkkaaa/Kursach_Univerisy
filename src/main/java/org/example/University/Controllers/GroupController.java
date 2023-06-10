package org.example.University.Controllers;

import org.example.University.Models.Group;
import org.example.University.Models.Specialty;
import org.example.University.Repositories.GroupRepository;
import org.example.University.Repositories.SpecialtyRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("group")
public class GroupController {
    private final GroupRepository groupRepository;
    private final SpecialtyRepository specialtyRepository;
    public GroupController(GroupRepository groupRepository, SpecialtyRepository specialtyRepository) {
        this.groupRepository = groupRepository;
        this.specialtyRepository = specialtyRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Group> groups = groupRepository.findAll();
        Iterable<Specialty> specialties = specialtyRepository.findAll();

        model.addAttribute("groups", groups);
        model.addAttribute("specialties", specialties);
        return "group";
    }
    @PostMapping("add")
    public String add(@RequestParam int number_of_students,
                      @RequestParam Specialty specialty) {
        Group group = new Group(number_of_students, specialty);
        groupRepository.save(group);
        return "redirect:/group";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Group group = groupRepository.findById(id).orElseThrow();
        groupRepository.delete(group);
        return "redirect:/group";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Group group = groupRepository.findById(id).orElseThrow();
        Iterable<Specialty> specialties = specialtyRepository.findAll();

        model.addAttribute("group", group);
        model.addAttribute("specialties", specialties);
        return "group-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam int number_of_students,
                       @RequestParam Specialty specialty) {
        Group group = groupRepository.findById(id).orElseThrow();
        group.setNumber_of_students(number_of_students);
        group.setSpecialty(specialty);
        groupRepository.save(group);
        return "redirect:/group";
    }
}
