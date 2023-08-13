package com.srh.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProductionProfile {
    @Autowired
    private DbSeeder dbSeeder;

    @Bean
    public boolean initializeDatabase() {
        return dbSeeder.seed();
    }
}
