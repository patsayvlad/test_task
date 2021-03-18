package com.task.test_task.controller;

import com.task.test_task.dto.Employee;
import com.task.test_task.service.employeeImpl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @PostMapping
    public void uploadEmployees() {
        employeeService.uploadEmployee();
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/with-max-salaries")
    public List<Employee> getEmployeesWithMaxSalaries(@RequestParam String department) {
        return employeeService.findAllWithMaxSalaryPerDepartment(department);
    }


    @GetMapping("/departments")
    public List<String> getDepartmentsWhereSalariesSortedDESC() {
        return employeeService.findAllDepartmentWithSortedSalaryDESC();
    }
}


