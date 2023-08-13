//package com.srh.api.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.srh.api.dto.resource.RecommendationForm;
//import com.srh.api.model.Recommendation;
//import com.srh.api.repository.RecommendationRepository;
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
//import java.time.LocalDateTime;
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
//public class RecommendationControllerTest {
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
//    private RecommendationRepository recommendationRepository;
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
//        List<Recommendation> recommendations = Arrays.asList(
//                new Recommendation(1, 0.5, 0.1, LocalDateTime.now(), null, null, null),
//                new Recommendation(2, 0.6, 0.2, LocalDateTime.now(), null, null, null),
//                new Recommendation(3, 0.7, 0.3, LocalDateTime.now(), null, null, null)
//        );
//
//        Page<Recommendation> pageRecommendations = new PageImpl<>(recommendations);
//
//        when(recommendationRepository.findAll(isA(Pageable.class))).thenReturn(pageRecommendations);
//        when(recommendationRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(recommendations.get(0)));
//        when(recommendationRepository.save(isA(Recommendation.class))).thenReturn(recommendations.get(0));
//    }
//
//    @Test
//    public void WhenGetAllRecommendationsThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/recommendations", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenGetRecommendationThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/recommendations/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenInsertRecommendationThenStatusCodeCreated() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/recommendations", port);
//        RecommendationForm recommendationForm = new RecommendationForm(0.5, 0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(recommendationForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(CREATED);
//    }
//
//    @Test
//    public void WhenUpdateRecommendationThenStatusCodeOk() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/recommendations/1", port);
//        RecommendationForm recommendationForm = new RecommendationForm(0.5, 0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(recommendationForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenDeleteRecommendationThenStatusCodeNoContent() {
//        String url = UrlUtils.generateBasicUrl("/recommendations/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
//    }
//
//    @Test
//    public void WhenGetAllRecommendationsWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/recommendations", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenGetRatingWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/recommendations/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenInsertRatingWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/recommendations", port);
//        RecommendationForm recommendationForm = new RecommendationForm(0.5, 0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(recommendationForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenUpdateRecommendationWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/recommendations/1", port);
//        RecommendationForm recommendationForm = new RecommendationForm(0.5, 0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(recommendationForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenDeleteRecommendationWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/recommendations/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//}
