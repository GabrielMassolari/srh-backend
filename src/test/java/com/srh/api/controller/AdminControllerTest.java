//package com.srh.api.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.srh.api.dto.resource.UserForm;
//import com.srh.api.model.Admin;
//import com.srh.api.repository.AdminRepository;
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
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.test.web.servlet.MockMvc;
//
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
//public class AdminControllerTest {
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
//    private AdminRepository adminRepository;
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
//        List<Admin> admins = Arrays.asList(
//                new Admin(1, "admin 1", "admin 1", "123", null),
//                new Admin(2, "admin 2", "admin 2", "345", null),
//                new Admin(3, "admin 3", "admin 3", "567", null)
//        );
//
//        Page<Admin> pageAdmins = new PageImpl<>(admins);
//
//        when(adminRepository.findAll(isA(Pageable.class))).thenReturn(pageAdmins);
//        when(adminRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(admins.get(0)));
//        when(adminRepository.save(isA(Admin.class))).thenReturn(admins.get(0));
//    }
//
//    @Test
//    public void WhenGetAllAdminsThenew Admin(1, "admin 1", "admin 1", "123", null),
//                new Admin(2, "admin 2", "admin 2", "345", null),
//                new Admin(3, "admin 3", "admin 3", "567", null)nStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/users/admins", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenGetAdminThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/users/admins/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenInsertAdminThenStatusCodeCreated() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/users/admins", port);
//        UserForm userForm = new UserForm("admin test", "admin test", "admin test");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(CREATED);
//    }
//
//    @Test
//    public void WhenUpdateAdminThenStatusCodeOk() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/users/admins/1", port);
//        UserForm userForm = new UserForm("admin test", "admin test", "admin test");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenDeleteAdminThenStatusCodeNoContent() {
//        String url = UrlUtils.generateBasicUrl("/users/admins/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
//    }
//
//    @Test
//    public void WhenGetAllAdminsWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/users/admins", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenGetAdminWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/users/admins", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenInsertAdminWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/users/admins", port);
//        UserForm userForm = new UserForm("admin test", "admin test", "admin test");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenUpdateAdminWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/users/admins/1", port);
//        UserForm userForm = new UserForm("admin test", "admin test", "admin test");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenDeleteAdminWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/users/admins/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//}
