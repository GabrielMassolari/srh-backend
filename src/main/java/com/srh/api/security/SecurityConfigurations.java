package com.srh.api.security;

import com.srh.api.repository.ApiUserRepository;
import com.srh.api.service.AuthService;
import com.srh.api.service.JWTService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthService authService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApiUserRepository APIUserRepository;

    @Override
    @SneakyThrows
    @Bean
    protected AuthenticationManager authenticationManager() {
        return super.authenticationManager();
    }

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/auth").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/itens").hasRole("ADMIN")
//                .antMatchers("/projects").hasRole("ADMIN")
//                .antMatchers("/users/admins").hasRole("ADMIN")
//                .antMatchers("/users/apis").hasRole("ADMIN")
//                .antMatchers("/recommendations/types").hasRole("ADMIN")
//                .anyRequest().hasRole("USER")
//                .and().cors()
//                .and().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().addFilterBefore(new AuthenticationWithTokenFilter(jwtService, APIUserRepository),
//                UsernamePasswordAuthenticationFilter.class);

        http.requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure();

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationWithTokenFilter(jwtService, APIUserRepository),
                UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    @Override
    @SneakyThrows
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}
