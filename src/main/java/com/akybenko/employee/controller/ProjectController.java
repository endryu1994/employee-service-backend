package com.akybenko.employee.controller;

import com.akybenko.employee.model.Employee;
import com.akybenko.employee.model.Project;
import com.akybenko.employee.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController extends AbstractController<Project, ProjectService> {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        super(projectService);
        this.projectService = projectService;
    }

    @PutMapping("/{projectId}/add/{employeeId}")
    public ResponseEntity<Employee> addEmployeeToProject(@PathVariable Long employeeId,
                                                         @PathVariable Long projectId) {
        return new ResponseEntity<>(projectService.addEmployeeToProject(projectId, employeeId), HttpStatus.OK);
    }

    @GetMapping("/name/{projectName}")
    public ResponseEntity<Project> getProjectByName(@PathVariable String projectName) {
        return new ResponseEntity<>(projectService.getProjectByName(projectName), HttpStatus.OK);
    }
}
