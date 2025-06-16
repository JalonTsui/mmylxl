package org.jalontsui.cloud.controller;

import jakarta.annotation.Resource;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-payment-service}")
    private String serviceUrl;

    @GetMapping("/getPortInfo")
    public ResultData<String> getPaymentInfo() {
        ResultData result = restTemplate.getForObject(serviceUrl + "/pay/port", ResultData.class);
        return result;
    }
}
