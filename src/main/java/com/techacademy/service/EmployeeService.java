package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techacademy.entity.Authentication;
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
    //レコード数のカウント
    public long index() {
        return employeeRepository.count();
    }


    //登録
    @Transactional
    public Employee saveEmployee(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setDeleteFlag(0);

        Authentication au = employee.getAuthentication();
        au.setEmployee(employee);
        employee.setAuthentication(au);

        return employeeRepository.save(employee);
    }
    //更新
    @Transactional
    public Employee updateEmployee(Employee employee) {
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setDeleteFlag(0);

        Authentication au = employee.getAuthentication();
        au.setEmployee(employee);
        employee.setAuthentication(au);

        return employeeRepository.save(employee);
    }


    @Transactional
    public void deleteEmployee(Integer id) {
        Employee employee = getEmployee(id);
        employee.setDeleteFlag(1);
        employeeRepository.save(employee);
        }

}
