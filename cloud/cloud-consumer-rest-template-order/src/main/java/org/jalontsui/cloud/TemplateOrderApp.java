package org.jalontsui.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope // consul动态刷新配置
public class TemplateOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(TemplateOrderApp.class, args);
    }
}
