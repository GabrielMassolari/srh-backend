//package com.srh.api.controller;
//
//import com.srh.api.utils.MockRequestBuilder;
//import com.srh.api.utils.RequestTokenUtil;
//import com.srh.api.utils.UrlUtils;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.http.HttpMethod.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class AuthControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AuthController authController;
//
//    private MockRequestBuilder requestBuilder;
//
//    public AuthControllerTest() {
//        this.requestBuilder = new MockRequestBuilder(mockMvc);
//    }
//
//    @Test
//    public void loginWithCorrectTokenThenSuccessuful() throws Exception {
//        String loginJson = RequestTokenUtil.buildBodyLoginRequest(true);
//        String url = UrlUtils.generateBasicUrl("/auth");
//
//        this.mockMvc.perform(requestBuilder.buildRequest(POST, url, loginJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void loginWithInvalidUserThenStatusCode401() throws Exception {
//        String loginJson = RequestTokenUtil.buildBodyLoginRequest(false);
//        String url = UrlUtils.generateBasicUrl("/auth");
//
//        this.mockMvc.perform(requestBuilder.buildRequest(POST, url, loginJson))
//                .andExpect(status().is4xxClientError());
//    }
//}
