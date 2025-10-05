package ru.itmo.softwaresecurity.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties
public class SoftwareSecurityLab1Main {

    public static void main(String[] args) {
        SpringApplication.run(SoftwareSecurityLab1Main.class, args);
    }
}
