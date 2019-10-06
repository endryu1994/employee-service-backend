package com.akybenko.employee.test;

import com.akybenko.employee.controller.ProjectController;
import com.akybenko.employee.model.Project;
import com.akybenko.employee.service.ProjectService;
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
@WebMvcTest(value = ProjectController.class)
public class ProjectServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    public void getProject() throws Exception {
        Mockito.when(projectService.get(Mockito.anyLong())).thenReturn(Utils.project());
        RequestBuilder builder = MockMvcRequestBuilders.get(GET_PROJECT_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        String expected =
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Test\",\n" +
                "    \"start\": null,\n" +
                "    \"finish\": null\n" +
                "}";
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void createProject() throws Exception {
        Mockito.when(projectService.create(Mockito.any(Project.class))).thenReturn(Utils.project());
        String body =
                "{\n" +
                "\t\"name\" : \"Test\",\n" +
                "\t\"start\" : null,\n" +
                "\t\"finish\" : null" +
                "}";
        RequestBuilder builder = MockMvcRequestBuilders.post(CREATE_PROJECT_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void findProjects() throws Exception {
        Mockito.when(projectService.findAll()).thenReturn(Collections.singletonList(Utils.project()));
        RequestBuilder builder =
                MockMvcRequestBuilders.get(FIND_PROJECTS_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        String expected =
                "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Test\",\n" +
                "        \"start\": null,\n" +
                "        \"finish\": null\n" +
                "    }\n" +
                "]";
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void updateProject() throws Exception {
        Mockito.when(projectService.update(Mockito.anyLong(), Mockito.any(Project.class)))
                .thenReturn(Utils.newProject());
        String body =
                "{\n" +
                "\t\"name\" : \"Test1\",\n" +
                "\t\"start\" : null,\n" +
                "\t\"finish\" : null\n" +
                "}";
        String expected =
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Test1\",\n" +
                "    \"start\": null,\n" +
                "    \"finish\": null\n" +
                "}";
        RequestBuilder builder = MockMvcRequestBuilders.put(EDIT_PROJECT_ENDPOINT)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void deleteProject() throws Exception {
        Mockito.when(projectService.delete(Mockito.anyLong())).thenReturn(ResponseEntity.noContent().build());
        RequestBuilder builder =
                MockMvcRequestBuilders.delete(DELETE_PROJECT_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

    @Test
    public void addEmployeeToProject() throws Exception {
        Mockito.when(projectService.addEmployeeToProject(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Utils.employeeWithProject());
        RequestBuilder builder =
                MockMvcRequestBuilders.put(ADD_PROJECT_FOR_EMPLOYEE_ENDPOINT).accept(MediaType.APPLICATION_JSON);
        String expected =
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Test123\",\n" +
                "    \"lastName\": \"Employee123\",\n" +
                "    \"dateOfBirth\": null,\n" +
                "    \"username\": \"test_employee\",\n" +
                "    \"email\": \"test@test.com\",\n" +
                "    \"phone\": \"9876543210\",\n" +
                "    \"project\": {\n" +
                "        \"name\": \"Test\"\n" +
                "    }\n" +
                "}";
        MvcResult result = mockMvc.perform(builder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}
