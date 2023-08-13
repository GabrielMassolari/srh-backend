//package com.srh.api.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.srh.api.dto.resource.ProjectForm;
//import com.srh.api.model.Project;
//import com.srh.api.model.Situations;
//import com.srh.api.repository.ProjectRepository;
//import com.srh.api.utils.RequestTokenUtil;
//import com.srh.api.utils.UrlUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static com.srh.api.utils.JsonUtil.toJson;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.HttpMethod.*;
//import static org.springframework.http.HttpStatus.*;
//import static org.springframework.http.HttpStatus.FORBIDDEN;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class ProjectControllerTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    Integer port;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProjectRepository projectRepository;
//
//    private HttpEntity<Void> validHeader;
//    private HttpEntity<Void> invalidHeader;
//
//    @BeforeEach
//    public void configureValidHeader() throws JsonProcessingException {
//        RequestTokenUtil requestTokenUtil = new RequestTokenUtil(restTemplate, port);
//        validHeader = requestTokenUtil.generateValidLoginHeaders();
//        invalidHeader = requestTokenUtil.generateInvalidLoginHeaders();
//    }
//
//    @BeforeEach
//    public void setup() {
//        List<Project> projects = Arrays.asList(
//                new Project(1, "project 1", "project 1", LocalDate.now(), Situations.OPEN, null, null, null),
//                new Project(2, "project 2", "project 2", LocalDate.now(), Situations.OPEN, null, null, null),
//                new Project(3, "project 3", "project 3", LocalDate.now(), Situations.OPEN, null, null, null)
//        );
//
//        Page<Project> pageProjects = new PageImpl<>(projects);
//
//        when(projectRepository.findAll(isA(Pageable.class))).thenReturn(pageProjects);
//        when(projectRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(projects.get(0)));
//        when(projectRepository.save(isA(Project.class))).thenReturn(projects.get(0));
//    }
//
//    @Test
//    public void WhenGetAllProjectsThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/projects", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenGetProjectThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/projects/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenInsertProjectThenStatusCodeCreated() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/projects", port);
//        ProjectForm projectForm = new ProjectForm("project", "project");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(projectForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(CREATED);
//    }
//
//    @Test
//    public void WhenUpdateProjectThenStatusCodeOk() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/projects/1", port);
//        ProjectForm projectForm = new ProjectForm("project", "project");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(projectForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenDeleteProjectThenStatusCodeNoContent() {
//        String url = UrlUtils.generateBasicUrl("/projects/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
//    }
//
//    @Test
//    public void WhenGetAllProjectsWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/projects", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenGetProjectWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/projects/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenInsertProjectWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/projects", port);
//        ProjectForm projectForm = new ProjectForm("project", "project");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(projectForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenUpdateProjectWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/projects/1", port);
//        ProjectForm projectForm = new ProjectForm("project", "project");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(projectForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenDeleteProjectWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/projects/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//}
