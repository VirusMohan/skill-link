package com.app.java.skill_link;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SkillLinkApplication {

    @Value("${DB_HOST}")
    private String dbHost;

    @Value("${DB_PORT}")
    private String dbPort;

    @Value("${DB_NAME}")
    private String dbName;

    public static void main(String[] args) {
        SpringApplication.run(SkillLinkApplication.class, args);
    }

    @PostConstruct
    public void printDbConfig() {
        System.out.println("DB Host: " + dbHost);
        System.out.println("DB Port: " + dbPort);
        System.out.println("DB Name: " + dbName);
    }
}
