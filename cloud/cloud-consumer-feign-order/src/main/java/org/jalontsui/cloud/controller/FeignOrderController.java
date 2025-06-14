package org.jalontsui.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.cloud.common.apis.PayFeignApi;
import org.jalontsui.cloud.common.entities.reqVo.PayReqVO;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign-order")
@Slf4j
public class FeignOrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/pay/add")
    public ResultData addOrder(@RequestBody PayReqVO payReqVO)
    {
        log.info("feign-order in");
        ResultData resultData = payFeignApi.addPay(payReqVO);
        return resultData;
    }

    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id)
    {
        ResultData resultData = payFeignApi.getById(id);
        return resultData;
    }

    @GetMapping("/test/server")
    public ResultData getServerPort()
    {
        ResultData resultData = payFeignApi.testServer();
        return resultData;
    }
}
