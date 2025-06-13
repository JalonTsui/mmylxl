package org.jalontsui.cloud.controller;

import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsulController {

    @GetMapping("/consul/test")
    public ResultData<String> testConsulConfig(@Value("${consul.info}") String info) {
        return ResultData.success(info);
    }
}
