package com.akybenko.employee.test.util;

import com.akybenko.employee.model.Employee;
import com.akybenko.employee.model.Project;

public class Utils {

    public static Employee employee() {
        Employee employee = new Employee();
        employee.setFirstName("Test");
        employee.setLastName("Employee");
        employee.setPhone("0123456789");
        employee.setEmail("test@test.com");
        employee.setUsername("test_employee");
        employee.setId(1L);

        return employee;
    }

    public static Employee newEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test123");
        employee.setLastName("Employee123");
        employee.setPhone("9876543210");
        employee.setEmail("test@test.com");
        employee.setUsername("test_employee");
        employee.setId(1L);

        return employee;
    }

    public static Employee employeeWithProject() {
        Employee employee = new Employee();
        employee.setFirstName("Test123");
        employee.setLastName("Employee123");
        employee.setPhone("9876543210");
        employee.setEmail("test@test.com");
        employee.setUsername("test_employee");
        employee.setId(1L);
        employee.setProject(project());

        return employee;
    }

    public static Project project() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Test");
        return project;
    }

    public static Project newProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Test1");
        return project;
    }
}