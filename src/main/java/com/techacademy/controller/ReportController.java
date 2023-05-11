package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }
    // 一覧画面
    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("reportlist",service.getReportList());
        model.addAttribute("reportcount", service.index());
        return "report/list";
    }
    // 詳細画面
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("report", service.getReport(id));
        return "report/detail";
    }
    // 登録画面
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Report report) {
        return "report/register";
    }
    // 登録処理
    @PostMapping("/register")
    public String postRegister(@Validated Report report, BindingResult res) {
        service.saveReport(report);
        return "redirect:/report/list";
    }

    // 更新画面
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("report", service.getReport(id));
        return "report/update";
    }
}
