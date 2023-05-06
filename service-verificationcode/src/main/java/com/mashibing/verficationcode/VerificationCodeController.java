package com.mashibing.verficationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VerificationCodeController {

    public static void main(String[] args) {
        SpringApplication.run(VerificationCodeController.class);
    }

}
