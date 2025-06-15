package org.jalontsui.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Micrometer链路追踪
 */

@RestController
public class PayMicrometerController {

    @GetMapping(value = "/pay/micrometer/{id}")
    public ResultData<String> myMicrometer(@PathVariable("id") Integer id) {
        return ResultData.success("Hello,欢迎使用micrometer inputId: " + id + "\t" + IdUtil.simpleUUID());
    }
}
