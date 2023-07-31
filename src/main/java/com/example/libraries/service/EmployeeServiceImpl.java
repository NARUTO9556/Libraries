package com.example.libraries.service;

import com.example.libraries.exception.EmployeeAlreadyAddedException;
import com.example.libraries.exception.EmployeeNotFoundException;
import com.example.libraries.exception.EmployeeStorageIsFullException;
import com.example.libraries.exception.InvalidInputException;
import com.example.libraries.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private final static int MAX_SIZE = 2;
    public EmployeeServiceImpl() {
        employeeList.add(new Employee("Иван", "Иванов", 1000.0, 1));
        employeeList.add(new Employee("Иван1", "Иванов1", 999.9, 1));
        employeeList.add(new Employee("Иван2", "Иванов2", 8.50, 1));
        employeeList.add(new Employee("Пётр", "Петров", 8.50, 2));
        employeeList.add(new Employee("Илья", "Ильин", 777.50, 3));
        employeeList.add(new Employee("Илья1", "Ильин1", 88.8, 3));
    }

    @Override
    public Employee add(String firstName, String lastName, double salary, int department) {
        validateInput(firstName,lastName);
        if (employeeList.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        Employee newEmployee = new Employee(firstName, lastName, salary, department);
        if (employeeList.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employeeList.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee remove(String firstName, String lastName, double salary, int department) {
        validateInput(firstName,lastName);
        Employee removeEmployee = new Employee(firstName, lastName, salary, department);
        boolean removeResult = employeeList.remove(removeEmployee);
        if (removeResult) {
            return removeEmployee;
        }else {
            throw new EmployeeNotFoundException("Сотрудник не был удален - не найден в базе");
        }
    }

    @Override
    public Employee find(String firstName, String lastName, double salary, int department) {
        validateInput(firstName,lastName);
        Employee findEmployee = new Employee(firstName, lastName, salary, department);
        for (Employee e : employeeList) {
            if (e.equals(findEmployee)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет в базе");
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeList;
    }

    public void validateInput(String firstName, String lastName) {
        if (isAlpha(firstName) && isAlpha(lastName)) {
            throw new InvalidInputException();
        }
    }

}
