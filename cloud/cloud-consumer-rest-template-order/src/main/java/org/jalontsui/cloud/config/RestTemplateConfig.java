package org.jalontsui.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced // 开启负载均衡，consul默认开启，否则会报错 UnknownHostException
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
