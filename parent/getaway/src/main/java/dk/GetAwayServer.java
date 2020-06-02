package dk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * A Camel Application
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class GetAwayServer {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        SpringApplication.run(GetAwayServer.class, args);
    }

}

