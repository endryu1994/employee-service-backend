package com.akybenko.employee.test.util;

public interface Constants {
    String GET_EMPLOYEE_ENDPOINT = "/api/v1/employee/1";
    String CREATE_EMPLOYEE_ENDPOINT = "/api/v1/employee/";
    String FIND_EMPLOYEE_ENDPOINT = "/api/v1/employee/";
    String EDIT_EMPLOYEE_ENDPOINT = "/api/v1/employee/1";
    String DELETE_EMPLOYEE_ENDPOINT = "/api/v1/employee/1";
    String DELETE_EMPLOYEE_PROJECT_ENDPOINT = "/api/v1/employee/1/deleteProject";

    String GET_PROJECT_ENDPOINT = "/api/v1/project/1";
    String CREATE_PROJECT_ENDPOINT = "/api/v1/project/";
    String FIND_PROJECTS_ENDPOINT = "/api/v1/project/";
    String EDIT_PROJECT_ENDPOINT = "/api/v1/project/1";
    String DELETE_PROJECT_ENDPOINT = "/api/v1/project/1";
    String ADD_PROJECT_FOR_EMPLOYEE_ENDPOINT = "/api/v1/project/1/add/1";
}
