package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
public class TopController {

    private final ReportService service;

    public TopController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getTop(Employee employee, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        model.addAttribute("employeereport",service.getEmployeeReport(userDetail.getEmployee()));
        model.addAttribute("reportcount", service.index());

        return "top";
    }
}
