package com.akybenko.employee.service;

import com.akybenko.employee.model.Employee;

public interface EmployeeService extends CommonService<Employee> {
    Employee deleteEmployeeProject(Long employeeId);
}
