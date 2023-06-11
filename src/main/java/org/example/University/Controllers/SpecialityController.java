package org.example.University.Controllers;

import org.example.University.Models.Specialty;
import org.example.University.Repositories.SpecialtyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("specialty")
public class SpecialityController {
    private final SpecialtyRepository specialtyRepository;
    public SpecialityController(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Specialty> specialties = specialtyRepository.findAll();

        model.addAttribute("specialties", specialties);
        return "specialty";
    }
    @PostMapping("add")
    public String add(@RequestParam String name,
                      @RequestParam int number_of_graduates) {
        Specialty specialty = new Specialty(name, number_of_graduates);
        specialtyRepository.save(specialty);
        return "redirect:/specialty";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow();
        specialtyRepository.delete(specialty);
        return "redirect:/specialty";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow();

        model.addAttribute("specialty", specialty);
        return "specialty-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name,
                       @RequestParam int number_of_graduates) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow();
        specialty.setName(name);
        specialty.setNumber_of_graduates(number_of_graduates);
        specialtyRepository.save(specialty);
        return "redirect:/specialty";
    }
}