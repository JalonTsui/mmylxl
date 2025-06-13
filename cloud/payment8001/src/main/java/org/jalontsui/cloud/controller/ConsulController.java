package org.jalontsui.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsulController {

    @GetMapping("/consul/test")
    public ResultData<String> testConsulConfig(@Value("${consul.info}") String info) {
        return ResultData.success(info);
    }

    @GetMapping("/consul/test/server")
    public ResultData<String> testServer(@Value("${server.port}") String port) {
        log.info("{}", port);
        return ResultData.success("this port is: " + port);
    }
}
