package com.srh.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcriptyUtil {
    public static boolean compareValues(String rawValue, String encriptedValue) {
        return new BCryptPasswordEncoder().matches(rawValue, encriptedValue);
    }

    public static String encripty(String rawValue) {
        return new BCryptPasswordEncoder().encode(rawValue);
    }
}
