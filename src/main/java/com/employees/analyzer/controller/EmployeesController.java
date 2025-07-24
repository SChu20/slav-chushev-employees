package com.employees.analyzer.controller;

import com.employees.analyzer.model.EmployeePairResult;
import com.employees.analyzer.service.EmployeesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class EmployeesController {

    private final EmployeesService es;

    public EmployeesController (EmployeesService employeesService) {
        this.es = employeesService;
    }

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<EmployeePairResult> results = es.findTopPair(file.getInputStream());
            model.addAttribute("results", results);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to parse file: " + e.getMessage());
        }
        return "index";
    }
}
