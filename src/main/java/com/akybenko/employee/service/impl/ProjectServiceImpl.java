package com.akybenko.employee.service.impl;

import com.akybenko.employee.exception.ResourceNotFoundException;
import com.akybenko.employee.model.Employee;
import com.akybenko.employee.model.Project;
import com.akybenko.employee.repository.EmployeeRepository;
import com.akybenko.employee.repository.ProjectRepository;
import com.akybenko.employee.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.akybenko.employee.constant.Constants.*;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Project get(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Project getProjectByName(String name) {
        return projectRepository.findByName(name).map(project -> {
            log.info("[ProjectServiceImpl#getProjectByName] :: " + project);
            return project;
        }).orElseThrow(() -> new ResourceNotFoundException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Project create(Project entity) {
        Project project = projectRepository.save(entity);
        log.info("[ProjectServiceImpl#create] :: " + project);
        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = projectRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        log.info("[ProjectServiceImpl#findProjects] :: findAll = " + projects.size());
        return projects;
    }

    @Override
    public Project update(Long id, Project entity) {
        return projectRepository.findById(id).map(project -> {
           log.info("[ProjectServiceImpl#updateProject] :: old = " + project);
           project.setName(entity.getName());
           project.setStart(entity.getStart());
           project.setFinish(entity.getFinish());
           log.info("[ProjectServiceImpl#updateProject] :: new = " + project);
           return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public ResponseEntity delete(Long id) {
        return projectRepository.findById(id).map(project -> {
            Optional<List<Employee>> employees = employeeRepository.findByProjectId(id);

            if (!employees.isPresent()) {
                projectRepository.delete(project);
                log.info("[ProjectServiceImpl#delete] :: " + project + " deleted");
            } else {
                log.info("[ProjectServiceImpl#delete] :: " + project + " cannot be deleted because it exists " +
                        employees.get().size() + " employee(s)");
                throw new RuntimeException(DELETE_PROJECT_EXCEPTION_MESSAGE);
            }

            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Employee addEmployeeToProject(Long projectId, Long employeeId) {
        return projectRepository.findById(projectId).map(project ->
                employeeRepository.findById(employeeId).map(employee -> {
                    employee.setProject(project);
                    log.info("[ProjectServiceImpl#addEmployeeToProject] :: " + project + " added for " + employee);
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE))
        ).orElseThrow(() -> new ResourceNotFoundException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}
