package org.jalontsui.sb.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.jalontsui.sb.log.entity.Echo3Query;
import org.springframework.web.bind.annotation.*;

/*
 * @author JalonTsui
 * @Date 21:32 2025/6/3
 **/

@Slf4j
@RestController
public class EchoController {
    // 路径参数
    @GetMapping("/echo/{message}")
    public String echo(@PathVariable("message") String message) {
        editLog();
        return message;
    }

    // query参数
    @GetMapping("/echo2")
    public String echo2(@RequestParam("message") String message) {
        editLog();
        return message;
    }

    // 请求体参数
    @PostMapping("/echo3")
    public Echo3Query echo3(@RequestBody Echo3Query query) {
        editLog();
        return query;
    }

    private void editLog() {
        for (int i = 0; i < 10; i++) {
            log.info("edit log");
        }
    }
}
