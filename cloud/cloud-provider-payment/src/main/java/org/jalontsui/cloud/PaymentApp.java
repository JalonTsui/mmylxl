package org.jalontsui.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.jalontsui.cloud.mapper")
@EnableDiscoveryClient // 允许服务的注册与发现
@RefreshScope // consul动态刷新配置
public class PaymentApp {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApp.class, args);
    }
}
