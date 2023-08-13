package com.srh.api.controller;

import com.srh.api.dto.auth.AuthDto;
import com.srh.api.dto.auth.LoginForm;
import com.srh.api.error.exception.InvalidUserInTokenException;
import com.srh.api.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.srh.api.security.SecurityConstants.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity<AuthDto> auth(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginUser = form.convert();
        String token = generatedTokenForAuthenticatedUser(loginUser);
        return ResponseEntity.ok()
                .headers(generateAuthorizationHeader(token))
                .body(new AuthDto(token, "Bearer"));
    }

    public String generatedTokenForAuthenticatedUser(UsernamePasswordAuthenticationToken userToken) {
        try {
            Authentication authentication = authManager.authenticate(userToken);
            return jwtService.buildToken(authentication);
        } catch (AuthenticationException e) {
            throw new InvalidUserInTokenException(e);
        }
    }

    private HttpHeaders generateAuthorizationHeader(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HEADER, TOKEN_PREFIX + token);
        return responseHeaders;
    }
}
