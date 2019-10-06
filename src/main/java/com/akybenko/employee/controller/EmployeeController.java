package com.akybenko.employee.controller;

import com.akybenko.employee.model.Employee;
import com.akybenko.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController extends AbstractController<Employee, EmployeeService> {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
        this.employeeService = employeeService;
    }

    @PutMapping("/{id}/deleteProject")
    public ResponseEntity<Employee> deleteEmployeeProject(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.deleteEmployeeProject(id), HttpStatus.OK);
    }
}
