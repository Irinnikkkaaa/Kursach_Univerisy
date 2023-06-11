package org.example.University.Controllers;

import org.example.University.Models.TypeOfReport;
import org.example.University.Repositories.TypeOfReportRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("type-of-report")
public class TypeOfReportController {
    private final TypeOfReportRepository typeOfReportRepository;
    public TypeOfReportController(TypeOfReportRepository typeOfReportRepository) {
        this.typeOfReportRepository = typeOfReportRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<TypeOfReport> typeOfReports = typeOfReportRepository.findAll();
        model.addAttribute("typeOfReports", typeOfReports);
        return "type-of-report";
    }
    @PostMapping("add")
    public String add(@RequestParam String name) {
        TypeOfReport typeOfReport = new TypeOfReport(name);
        typeOfReportRepository.save(typeOfReport);
        return "redirect:/type-of-report";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        TypeOfReport typeOfReport = typeOfReportRepository.findById(id).orElseThrow();
        typeOfReportRepository.delete(typeOfReport);
        return "redirect:/type-of-report";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        TypeOfReport typeOfReport = typeOfReportRepository.findById(id).orElseThrow();
        model.addAttribute("typeOfReport", typeOfReport);
        return "type-of-report-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name) {
        TypeOfReport typeOfReport = typeOfReportRepository.findById(id).orElseThrow();
        typeOfReport.setName(name);
        typeOfReportRepository.save(typeOfReport);
        return "redirect:/type-of-report";
    }
}