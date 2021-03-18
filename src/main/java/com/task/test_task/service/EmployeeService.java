package com.task.test_task.service;

import com.task.test_task.dto.Employee;

import java.util.List;

public interface EmployeeService {

    void uploadEmployee();

    List<Employee> findAll();

    List<Employee> findAllWithMaxSalaryPerDepartment(String department);

    List<String> findAllDepartmentWithSortedSalaryDESC();

}
