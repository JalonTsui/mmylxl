package org.jalontsui.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public ResultData<String> getPort() {
        return ResultData.success("hello nacos, this port is: " + port + "\t" + IdUtil.simpleUUID());
    }
}
