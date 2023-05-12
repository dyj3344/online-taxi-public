package com.mashibing.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceMapController {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMapController.class);
    }

    @Bean
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }
}
