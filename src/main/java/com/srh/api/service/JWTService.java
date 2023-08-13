package com.srh.api.service;

import com.srh.api.model.ApiUser;
import com.srh.api.utils.UserApiJwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${srh.jwt.expiration}")
    private String tokenExpirationTime;

    @Value("${srh.jwt.secret}")
    private String secret;

    @Autowired
    private UserApiJwtUtil userApiJwtUtil;

    public String buildToken(Authentication authentication) {
        ApiUser userLogged = (ApiUser) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(tokenExpirationTime));
        return buildJWT(userLogged, today, expirationDate);
    }

    public boolean isValidJWT(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getUserId(String token) {
        return userApiJwtUtil.getUserId(token);
    }

    private String buildJWT(ApiUser user, Date today, Date expirationDate) {
        return Jwts.builder()
                .setIssuer("Hybrid Recommendation System - SRH")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
