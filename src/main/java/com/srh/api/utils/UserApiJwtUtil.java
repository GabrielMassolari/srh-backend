package com.srh.api.utils;

import com.srh.api.model.Admin;
import com.srh.api.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserApiJwtUtil {
    @Value("${srh.jwt.secret}")
    private String secret;

    @Autowired
    private AdminRepository adminRepository;

    public boolean verifyIfUserIsAdmin(String token) {
        Integer id = getUserId(token);
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.isPresent();
    }

    public Integer getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}
