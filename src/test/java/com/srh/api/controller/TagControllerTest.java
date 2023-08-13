//package com.srh.api.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.srh.api.dto.resource.TagForm;
//import com.srh.api.model.Tag;
//import com.srh.api.repository.TagRepository;
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
//import static com.srh.api.utils.JsonUtil.*;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//import static org.springframework.http.HttpMethod.*;
//import static org.springframework.http.HttpStatus.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class TagControllerTest {
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
//    private TagRepository tagRepository;
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
//        List<Tag> tags = Arrays.asList(
//                new Tag(1, "Test 1", null),
//                new Tag(2, "Test 2", null),
//                new Tag(3, "Test 3", null)
//        );
//
//        Page<Tag> pageTags = new PageImpl<>(tags);
//
//        when(tagRepository.findAll(isA(Pageable.class))).thenReturn(pageTags);
//        when(tagRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(tags.get(0)));
//        when(tagRepository.save(isA(Tag.class))).thenReturn(tags.get(0));
//    }
//
//    @Test
//    public void WhenGetAllTagsThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/tags", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenGetTagThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/tags/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenInsertTagThenStatusCodeCreated() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/tags", port);
//        TagForm tagForm = new TagForm("Test");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(tagForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(CREATED);
//    }
//
//    @Test
//    public void WhenUpdateTagThenStatusCodeOk() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/tags/1", port);
//        TagForm tagForm = new TagForm("Test Update");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(tagForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenDeleteTagThenStatusCodeNoContent() {
//        String url = UrlUtils.generateBasicUrl("/tags/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
//    }
//
//    @Test
//    public void WhenGetAllTagsWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/tags", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenGetTagWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/tags/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenInsertTagWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/tags", port);
//        TagForm tagForm = new TagForm("Test");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(tagForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenUpdateTagWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/tags/1", port);
//        TagForm tagForm = new TagForm("Test Update");
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(tagForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenDeleteTagWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/tags/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//}
