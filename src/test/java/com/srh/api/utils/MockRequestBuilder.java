package com.srh.api.utils;

import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.srh.api.utils.TestConstants.*;
import static org.springframework.http.MediaType.*;

public class MockRequestBuilder {
    private MockMvc mockMvc;

    public MockRequestBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public MockHttpServletRequestBuilder buildRequest(HttpMethod httpMethod, String url, String json) {
        return executeRequest(httpMethod, url)
                .content(json)
                .contentType(APPLICATION_JSON);
    }

    private MockHttpServletRequestBuilder executeRequest(HttpMethod method, String url) {
        return MockMvcRequestBuilders.request(method, url);
    }
}
