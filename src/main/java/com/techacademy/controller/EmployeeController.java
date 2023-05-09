package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    // 一覧画面
    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("employeelist", service.getEmployeeList());
        return "employee/list";
    }
    // 詳細画面
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", service.getEmployee(id));
        return "employee/detail";
    }
    // 登録画面
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        return "employee/register";
    }
    // 登録処理
    @PostMapping("/register")
    public String postRegister(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }
    // 更新画面
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", service.getEmployee(id));
        return "employee/update";
    }
    // 更新処理
    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, @RequestParam("password")String password, Employee employee, Model model) {

    model.addAttribute("employee", service.getEmployee(id));
    if(!"".equals(password)) {
        employee.getAuthentication().setPassword(password);
    }
    service.saveEmployee(employee);
        return "redirect:/employee/list";
    }
    // 削除処理
    @GetMapping("/delete/{id}")
    public String deleteRun(@PathVariable("id") Integer id, Model model) {

        service.deleteEmployee(id);
        return "redirect:/employee/list";
    }
}
