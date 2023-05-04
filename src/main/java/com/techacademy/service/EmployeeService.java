package com.techacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService (EmployeeRepository repository) {
        this.employeeRepository = repository;
    }
    //全件検索
    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }
    //一件検索
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }
    //登録
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

}
