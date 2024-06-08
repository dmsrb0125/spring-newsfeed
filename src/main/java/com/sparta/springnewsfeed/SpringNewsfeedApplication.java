package com.sparta.springnewsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing // Timestamped 작동시키기위한 annotation
@SpringBootApplication
public class SpringNewsfeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNewsfeedApplication.class, args);
    }

}