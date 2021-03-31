package edu.umass.orgitect.stages_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(
    basePackages = {
            "edu.umass.orgitect.stages_service"
    })
@EnableConfigurationProperties
public class StagesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StagesServiceApplication.class, args);
    }
}
