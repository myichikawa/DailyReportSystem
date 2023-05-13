package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

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
    public String getRegister(Report report, @AuthenticationPrincipal UserDetail userDetail, Model model) {

        model.addAttribute("report", report);
        model.addAttribute("loginuser", userDetail.getEmployee());
        return "report/register";
    }
    // 登録処理
    @PostMapping("/register")
    public String postRegister(@Validated Report report, @AuthenticationPrincipal UserDetail userDetail, Model model, BindingResult res) {

        if(res.hasErrors()) {
            // エラーあり
            return getRegister(report, userDetail, model);
        }

        report.setEmployee(userDetail.getEmployee());
        service.saveReport(report);
        return "redirect:/report/list";
    }

    // 更新画面
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        model.addAttribute("loginuser", userDetail.getEmployee());
        model.addAttribute("report", service.getReport(id));
        return "report/update";
    }

    // 更新処理
    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, Report report, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        model.addAttribute("loginuser", userDetail.getEmployee());
        model.addAttribute("report", service.getReport(id));

        Report tableReport = service.getReport(id);
        report.setCreatedAt(tableReport.getCreatedAt());
        service.updateReport(report);
            return "redirect:/Report/list";

    }
}
