package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.service.EmployeeService;

@Controller

public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employee/list")
    public String getList(Model model) {
        model.addAttribute("employeelist", service.getEmployeeList());
        return "employee/list";
    }
    @GetMapping("/employee/detail")
    public String getDetail() {
        return "employee/detail";
    }
    @GetMapping("/employee/resister")
    public String getResister() {
        return "employee/resister";
    }
    @GetMapping("/employee/update")
    public String getUpdate() {
        return "employee/update";
    }
}

