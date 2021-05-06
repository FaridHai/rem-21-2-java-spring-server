package de.neuefische.springserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringServerApplication.class, args);
    }

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
