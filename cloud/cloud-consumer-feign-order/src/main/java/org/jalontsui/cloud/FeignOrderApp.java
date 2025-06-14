package org.jalontsui.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // 开启OpenFeign功能
public class FeignOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderApp.class, args);
    }
}
