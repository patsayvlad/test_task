package com.task.test_task.service.employeeImpl;

import com.task.test_task.entity.Employee;
import com.task.test_task.entity.Employees;
import com.task.test_task.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final File file = new File("src/main/resources/static/data.csv");
    @Resource(name = "getEmployees")
    private Employees employees;

    @Override
    public void uploadEmployee() {
        if (employees.getEmployees().size()!=0){
            employees.setEmployees(new ArrayList<>());
        }
        try (BufferedReader csvReader = new BufferedReader(new FileReader(file))) {
            String row;
            int i = 0;
            while ((row = csvReader.readLine()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                String[] data = row.split(";");
                employees.getEmployees().add(new Employee(data[0], data[1], Integer.parseInt(data[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAll() {
        return employees.getEmployees();
    }

    @Override
    public List<Employee> findAllWithMaxSalaryPerDepartment(String department) {
        Optional<Employee> max = employees.getEmployees().stream().filter(x -> x.getDepartment().equalsIgnoreCase(department)).max(Comparator.comparing(Employee::getSalary));
        return employees.getEmployees().stream().filter(x -> x.getDepartment().equalsIgnoreCase(department)).filter(x -> x.getSalary().equals(max.get().getSalary())).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllDepartmentWithSortedSalaryDESC() {

        List<String> departments = employees.getEmployees().stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
        List<String> fitDepartments = new ArrayList<>();


        for (String temp : departments) {
            List<Employee> employeesPerDepartment = employees.getEmployees().stream().filter(x -> x.getDepartment().equalsIgnoreCase(temp)).collect(Collectors.toList());
            List<Employee> sortedEmployeesPerDepartment = employees.getEmployees().stream().filter(x -> x.getDepartment().equalsIgnoreCase(temp)).sorted((o1, o2) -> (-1) * o1.getSalary().compareTo(o2.getSalary())).collect(Collectors.toList());
            int bound = employeesPerDepartment.size();
            boolean b = true;
            for (int i = 0; i < bound; i++) {
                if (!(employeesPerDepartment.get(i).getSalary().equals(sortedEmployeesPerDepartment.get(i).getSalary()))){
                    b = false;
                    break;
                }
            }
            if (b){
                fitDepartments.add(temp);
            }
        }

        return fitDepartments;
    }
}
