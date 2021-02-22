package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration
@ActiveProfiles("test-mysql")
@TestPropertySource("classpath:application-test-mysql.properties")
public class BaseTestConfig {

    @Value("${spring.datasource.name}")
    private String dbName;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;


   @Bean
    MySQLContainer mySQLContainer() {
        MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:latest")
                .withDatabaseName(dbName)
                .withUsername(userName)
                .withPassword(password)
                .withReuse(true);

        mySQLContainer.start();

        return mySQLContainer;
    }
}
