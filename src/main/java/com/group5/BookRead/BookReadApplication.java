package com.group5.BookRead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class BookReadApplication {
    public static void main(final String[] args) {
        SpringApplication.run(BookReadApplication.class, args);
    }

    @Bean
    public static RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
