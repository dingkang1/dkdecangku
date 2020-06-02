package com.dk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * A Camel Application
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
@EnableFeignClients
public class CommonServer {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        SpringApplication.run(CommonServer.class, args);
    }

}

