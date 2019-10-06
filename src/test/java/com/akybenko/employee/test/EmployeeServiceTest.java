package com.akybenko.employee.test;

import com.akybenko.employee.controller.EmployeeController;
import com.akybenko.employee.model.Employee;
import com.akybenko.employee.service.EmployeeService;
import com.akybenko.employee.test.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static com.akybenko.employee.test.util.Constants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployee() throws Exception {
        Mockito.when(employeeService.get(Mockito.anyLong())).thenReturn(Utils.employee());
        RequestBuilder builder = MockMvcRequestBuilders.get(GET_EMPLOYEE_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        String expected =
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Test\",\n" +
                "    \"lastName\": \"Employee\",\n" +
                "    \"dateOfBirth\": null,\n" +
                "    \"username\": \"test_employee\",\n" +
                "    \"email\": \"test@test.com\",\n" +
                "    \"phone\": \"0123456789\",\n" +
                "    \"project\": null\n" +
                "}";
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void createEmployee() throws Exception {
        Mockito.when(employeeService.create(Mockito.any(Employee.class))).thenReturn(Utils.employee());
        String body =
                "{\n" +
                "\t\"firstName\" : \"Test\",\n" +
                "\t\"lastName\" : \"Employee\",\n" +
                "\t\"dateOfBirth\" : \"1990-05-21\",\n" +
                "\t\"username\" : \"test_employee\",\n" +
                "\t\"email\" : \"test@test.com\",\n" +
                "\t\"phone\" : \"0123456789\"\n" +
                "}";
        RequestBuilder builder = MockMvcRequestBuilders.post(CREATE_EMPLOYEE_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void findEmployees() throws Exception {
        Mockito.when(employeeService.findAll()).thenReturn(Collections.singletonList(Utils.employee()));
        RequestBuilder builder =
                MockMvcRequestBuilders.get(FIND_EMPLOYEE_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        String expected =
                "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"firstName\": \"Test\",\n" +
                "        \"lastName\": \"Employee\",\n" +
                "        \"dateOfBirth\": null,\n" +
                "        \"username\": \"test_employee\",\n" +
                "        \"email\": \"test@test.com\",\n" +
                "        \"phone\": \"0123456789\",\n" +
                "        \"project\": null\n" +
                "    }\n" +
                "]";
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void updateEmployee() throws Exception {
        Mockito.when(employeeService.update(Mockito.anyLong(), Mockito.any(Employee.class)))
                .thenReturn(Utils.newEmployee());
        String body =
                "{\n" +
                "    \"firstName\": \"Test123\",\n" +
                "    \"lastName\": \"Employee123\",\n" +
                "    \"dateOfBirth\": null,\n" +
                "    \"username\": \"test_employee\",\n" +
                "    \"email\": \"test@test.com\",\n" +
                "    \"phone\": \"9876543210\"\n" +
                "}";
        String expected =
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Test123\",\n" +
                "    \"lastName\": \"Employee123\",\n" +
                "    \"dateOfBirth\": null,\n" +
                "    \"username\": \"test_employee\",\n" +
                "    \"email\": \"test@test.com\",\n" +
                "    \"phone\": \"9876543210\",\n" +
                "    \"project\": null\n" +
                "}";
        RequestBuilder builder = MockMvcRequestBuilders.put(EDIT_EMPLOYEE_ENDPOINT)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void deleteEmployee() throws Exception {
        Mockito.when(employeeService.delete(Mockito.anyLong())).thenReturn(ResponseEntity.noContent().build());
        RequestBuilder builder =
                MockMvcRequestBuilders.delete(DELETE_EMPLOYEE_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

    @Test
    public void deleteEmployeeProject() throws Exception {
        Mockito.when(employeeService.deleteEmployeeProject(Mockito.anyLong())).thenReturn(Utils.employee());
        RequestBuilder builder =
                MockMvcRequestBuilders.put(DELETE_EMPLOYEE_PROJECT_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        String expected =
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Test\",\n" +
                "    \"lastName\": \"Employee\",\n" +
                "    \"dateOfBirth\": null,\n" +
                "    \"username\": \"test_employee\",\n" +
                "    \"email\": \"test@test.com\",\n" +
                "    \"phone\": \"0123456789\",\n" +
                "    \"project\": null\n" +
                "}";
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}
