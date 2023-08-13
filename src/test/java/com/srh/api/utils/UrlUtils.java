package com.srh.api.utils;

public class UrlUtils {
    public static final String baseUrl = "http://localhost:8080";
    public static final String baseUrlWithoutPort = "http://localhost";

    public static String generateBasicUrl(String uri) {
        return baseUrl + uri;
    }

    public static String generateBasicUrl(String uri, Integer port) {
        return baseUrlWithoutPort + ":" + port + uri;
    }
}
