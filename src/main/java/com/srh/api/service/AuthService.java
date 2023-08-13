package com.srh.api.service;

import com.srh.api.model.ApiUser;
import com.srh.api.repository.ApiUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApiUserRepository repository;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String login) {
        Optional<ApiUser> user = repository.findByLogin(login);
        if (user.isPresent())
            return user.get();

        throw new UsernameNotFoundException("Invalid user data");
    }
}
