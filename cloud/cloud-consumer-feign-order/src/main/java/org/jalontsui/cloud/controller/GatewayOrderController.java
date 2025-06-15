package org.jalontsui.cloud.controller;

import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import org.jalontsui.cloud.common.apis.PayFeignWithGatewayApi;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.jalontsui.cloud.common.entities.respVo.PayRespVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟外部调用，假设该接口是给外部调用的
 */

@RestController
@RequestMapping("/order/gateway")
public class GatewayOrderController {
    @Resource
    private PayFeignWithGatewayApi payFeignWithGatewayApi;

    @GetMapping(value = "/get/{id}")
    public ResultData<PayRespVO> getById(@PathVariable("id") Integer id) {
        ResultData<PayRespVO> pay = payFeignWithGatewayApi.getById(id);
        return pay;
    }

    @GetMapping(value = "/info")
    public ResultData<String> getGatewayInfo() {
        ResultData<String> gatewayInfo = payFeignWithGatewayApi.getGatewayInfo();
        return gatewayInfo;
    }
}
