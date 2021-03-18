package com.task.test_task.config;

import com.task.test_task.dto.Employees;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;

@Configuration
public class EmployeesConfig {

    @Bean
    @SessionScope
    public Employees getEmployees() {
        Employees employees = new Employees();
        employees.setEmployees(new ArrayList<>());
        return employees;
    }

}
