package com.srh.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestProfile {
    @Autowired
    private DbSeeder dbSeeder;

    @Bean
    public boolean initializeDatabase() {
        return dbSeeder.seed();
    }
}
