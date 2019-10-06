package com.akybenko.employee.service;

import com.akybenko.employee.model.Employee;
import com.akybenko.employee.model.Project;

public interface ProjectService extends CommonService<Project> {
    Project getProjectByName(String name);
    Employee addEmployeeToProject(Long projectId, Long employeeId);
}
