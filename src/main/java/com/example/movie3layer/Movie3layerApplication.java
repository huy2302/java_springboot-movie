package com.example.movie3layer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class Movie3layerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Movie3layerApplication.class, args);
    }
}
