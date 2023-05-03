package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("employeelist", service.getEmployeeList());
        return "employee/list";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable Integer id, Model model) {

        model.addAttribute("employee", service.getEmployee(id));
        return "employee/detail";
    }


    @GetMapping("/resister")
    public String getResister() {
        return "employee/resister";
    }
    @GetMapping("/update")
    public String getUpdate() {
        return "employee/update";
    }
}

