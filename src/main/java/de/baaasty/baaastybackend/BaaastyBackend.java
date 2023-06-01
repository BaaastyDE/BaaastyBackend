package de.baaasty.baaastybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BaaastyBackend {

    public static void main(String[] args) {
        SpringApplication.run(BaaastyBackend.class, args);
    }

}
