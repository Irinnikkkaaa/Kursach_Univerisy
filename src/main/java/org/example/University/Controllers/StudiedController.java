package org.example.University.Controllers;

import org.example.University.Models.Discipline;
import org.example.University.Models.Specialty;
import org.example.University.Models.Studied;
import org.example.University.Models.TypeOfReport;
import org.example.University.Repositories.DisciplineRepository;
import org.example.University.Repositories.SpecialtyRepository;
import org.example.University.Repositories.StudiedRepository;
import org.example.University.Repositories.TypeOfReportRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("studied")
public class StudiedController {
    private final StudiedRepository studiedRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DisciplineRepository disciplineRepository;
    private final TypeOfReportRepository typeOfReportRepository;

    public StudiedController(StudiedRepository studiedRepository, SpecialtyRepository specialtyRepository, DisciplineRepository disciplineRepository, TypeOfReportRepository typeOfReportRepository) {
        this.studiedRepository = studiedRepository;
        this.specialtyRepository = specialtyRepository;
        this.disciplineRepository = disciplineRepository;
        this.typeOfReportRepository = typeOfReportRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Studied> studieds = studiedRepository.findAll();
        Iterable<Specialty> specialties = specialtyRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        Iterable<TypeOfReport> typeOfReports = typeOfReportRepository.findAll();

        model.addAttribute("studieds", studieds);
        model.addAttribute("specialties", specialties);
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("typeOfReports", typeOfReports);

        return "studied";
    }
    @PostMapping("add")
    public String add(@RequestParam int number_of_hours,
                      @RequestParam Specialty specialty,
                      @RequestParam Discipline discipline,
                      @RequestParam TypeOfReport typeOfReport) {
        Studied studied = new Studied(number_of_hours, specialty, discipline, typeOfReport);
        studiedRepository.save(studied);
        return "redirect:/studied";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Studied studied = studiedRepository.findById(id).orElseThrow();
        studiedRepository.delete(studied);
        return "redirect:/studied";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Studied studied = studiedRepository.findById(id).orElseThrow();
        Iterable<Specialty> specialties = specialtyRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        Iterable<TypeOfReport> typeOfReports = typeOfReportRepository.findAll();

        model.addAttribute("studied", studied);
        model.addAttribute("specialties", specialties);
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("typeOfReports", typeOfReports);
        return "studied-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam int number_of_hours,
                       @RequestParam Specialty specialty,
                       @RequestParam Discipline discipline,
                       @RequestParam TypeOfReport typeOfReport) {
        Studied studied = studiedRepository.findById(id).orElseThrow();
        studied.setNumber_of_hours(number_of_hours);
        studied.setSpecialty(specialty);
        studied.setDiscipline(discipline);
        studied.setTypeOfReport(typeOfReport);
        studiedRepository.save(studied);
        return "redirect:/studied";
    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) Specialty specialty,
                         Model model) {
        Iterable<Studied> studieds = studiedRepository.findAll();
        Iterable<Specialty> specialties = specialtyRepository.findAll();
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        Iterable<TypeOfReport> typeOfReports = typeOfReportRepository.findAll();

        if(specialty != null) {
            studieds = studiedRepository.findBySpecialty(specialty);
        }

        model.addAttribute("studieds", studieds);
        model.addAttribute("specialties", specialties);
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("typeOfReports", typeOfReports);

        return "studied";
    }
}