package org.jalontsui.cloud.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.cloud.common.apis.PayFeignApi;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/circuit")
@Slf4j
public class CircuitOrderController {

    @Resource
    private PayFeignApi payFeignApi;


    /**
     * 服务熔断注解
     *
     * @param id
     * @return
     */
    @GetMapping("/test/breaker/{id}")
    @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "breakerFallBack")
    public ResultData<String> circuitBreakerTest(@PathVariable("id") Integer id) {
        ResultData<String> resultData = payFeignApi.myCircuit(id);
        return resultData;
    }

    /**
     * 信号量舱壁测试，控制接口并发数
     *
     * @param id
     * @return
     */
    @GetMapping("/test/bulkhead/{id}")
    @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "breakerFallBack", type = Bulkhead.Type.SEMAPHORE)
    public ResultData<String> circuitBulkheadTest(@PathVariable("id") Integer id) {
        ResultData<String> resultData = payFeignApi.myCircuit(id);
        return resultData;
    }

    @GetMapping("/test/rate-limit/{id}")
    @RateLimiter(name = "cloud-provider-payment", fallbackMethod = "breakerFallBack")
    public ResultData<String> circuitRateLimitTest(@PathVariable("id") Integer id) {
        ResultData<String> resultData = payFeignApi.myCircuit(id);
        return resultData;
    }


    /**
     * fallback函数必须要要带上与接口一样的入参，且后面要根Throwable，否则无法识别
     *
     * @param id
     * @param t
     * @return
     */
    public ResultData<String> breakerFallBack(Integer id, Throwable t) {
        log.error("{}", t.getMessage());
        return ResultData.fail("500", "server is gg");
    }
}
