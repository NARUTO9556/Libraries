package com.example.libraries.controller;

import com.example.libraries.model.Employee;
import com.example.libraries.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam int department) {
        return employeeService.add(firstName, lastName, salary, department);
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam int department) {
        return employeeService.remove(firstName, lastName, salary, department);
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam int department) {
        return employeeService.find(firstName, lastName, salary, department);
    }

    @GetMapping
    public Collection<Employee> findALl() {
        return employeeService.findAll();
    }
}
