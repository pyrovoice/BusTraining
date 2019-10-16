package com.grazzini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.grazzini.dao"})
public class BusTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusTrainingApplication.class, args);
    }

}
