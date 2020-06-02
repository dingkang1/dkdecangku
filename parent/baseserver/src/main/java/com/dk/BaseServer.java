package com.dk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = "com.dk")
@EnableConfigurationProperties
@EnableDiscoveryClient
public class BaseServer {
    public static void main(String... args) throws Exception {
        SpringApplication.run(BaseServer.class, args);
    }
}
