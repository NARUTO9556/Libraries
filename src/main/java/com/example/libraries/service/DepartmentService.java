package com.example.libraries.service;

import com.example.libraries.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(Integer department);
    Employee getEmployeeWithMinSalary(Integer department);
    Map<Integer, List<Employee>> getEmployeeByDepartment(Integer department);
}
