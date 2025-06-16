package org.jalontsui.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentApp.class, args);
    }
}
