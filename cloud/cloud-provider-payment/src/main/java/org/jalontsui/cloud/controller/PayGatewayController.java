package org.jalontsui.cloud.controller;

import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.jalontsui.cloud.common.entities.respVo.PayRespVO;
import org.jalontsui.cloud.service.PayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/gateway")
public class PayGatewayController {
    @Resource
    PayService payService;

    @GetMapping(value = "/get/{id}")
    public ResultData<PayRespVO> getById(@PathVariable("id") Integer id)
    {
        PayRespVO pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/info")
    public ResultData<String> getGatewayInfo()
    {
        return ResultData.success("gateway info test："+ IdUtil.simpleUUID());
    }
}
