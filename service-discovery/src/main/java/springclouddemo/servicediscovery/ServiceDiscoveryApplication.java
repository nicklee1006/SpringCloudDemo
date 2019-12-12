package springclouddemo.servicediscovery;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDiscoveryApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceDiscoveryApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
