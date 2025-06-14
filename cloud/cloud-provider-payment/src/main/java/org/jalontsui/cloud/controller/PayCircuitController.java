package org.jalontsui.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/circuit")
public class PayCircuitController {

    @GetMapping("/pay/{id}")
    public ResultData<String> myCircuit(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("-----circuit id 不能为负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ResultData.success("Hello, circuit! inputId:  " + "\t" + IdUtil.simpleUUID());
    }
}
