package com.freshsip.billservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BillServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillServiceApplication.class, args);
    }
    @Bean
    public ModelMapper modalMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
