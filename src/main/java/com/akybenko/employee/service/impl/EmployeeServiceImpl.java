package com.akybenko.employee.service.impl;

import com.akybenko.employee.exception.ResourceNotFoundException;
import com.akybenko.employee.model.Employee;
import com.akybenko.employee.repository.EmployeeRepository;
import com.akybenko.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.akybenko.employee.constant.Constants.EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee get(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Employee create(Employee entity) {
        Employee employee = employeeRepository.save(entity);
        log.info("[EmployeeServiceImpl#create] :: " + employee);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        log.info("[EmployeeServiceImpl#findAll] :: countEmployees = " + employees.size());
        return employees;
    }

    @Override
    public Employee update(Long id, Employee entity) {
        return employeeRepository.findById(id).map(employee -> {
            log.info("[EmployeeServiceImpl#update] :: old = " + employee);
            employee.setFirstName(entity.getFirstName());
            employee.setLastName(entity.getLastName());
            employee.setUsername(entity.getUsername());
            employee.setEmail(entity.getEmail());
            employee.setDateOfBirth(entity.getDateOfBirth());
            employee.setPhone(entity.getPhone());
            log.info("[EmployeeServiceImpl#update] :: new = " + employee);
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public ResponseEntity delete(Long id) {
        return employeeRepository.findById(id).map(employee -> {
            employeeRepository.delete(employee);
            log.info("[EmployeeServiceImpl#deleteEmployee] :: " + employee + " deleted");
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Employee deleteEmployeeProject(Long id) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setProject(null);
            log.info("[EmployeeServiceImpl#deleteEmployeeProject] :: Project deleted for " + employee);
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}