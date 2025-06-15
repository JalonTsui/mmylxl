package org.jalontsui.cloud.controller;

import jakarta.annotation.Resource;
import org.jalontsui.cloud.common.apis.PayFeignApi;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/feign/micrometer")
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/{id}")
    public ResultData<String> micrometerTest(@PathVariable("id") Integer id) {
        return payFeignApi.micrometerTest(id);
    }
}
