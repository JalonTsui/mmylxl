package org.jalontsui.cloud.controller;

import jakarta.annotation.Resource;
import org.jalontsui.cloud.common.entities.reqVo.PayReqVO;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    // private static final String PAY_URL = "http://127.0.0.1:8001";
    private static final String PAY_URL = "http://payment8001";
    @Resource
    private RestTemplate restTemplate;

    // body参数一定要加@RequestBody注解，否则会被解析成query参数
    @GetMapping("/pay/add")
    public ResultData addOrder(@RequestBody PayReqVO payReqVO) {
        return restTemplate.postForObject(PAY_URL + "/pay/add", payReqVO, ResultData.class);
    }

    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAY_URL + "/pay/get/" + id, ResultData.class);
    }
}
