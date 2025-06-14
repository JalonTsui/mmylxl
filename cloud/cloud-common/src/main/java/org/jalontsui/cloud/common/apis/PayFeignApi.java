package org.jalontsui.cloud.common.apis;

import org.jalontsui.cloud.common.entities.reqVo.PayReqVO;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.jalontsui.cloud.common.entities.respVo.PayRespVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 配置openfeign接口，绑定注册到consul的微服务名字，调用服务时不需要再用restTemplate调用，改为接口注入调用
 * 把需要暴露出去的接口暴露出去即可
 *
 * 进阶的配置使用，都是些application.yaml的配置项，查看官网使用即可
 */
@FeignClient(value = "cloud-provider-payment")
public interface PayFeignApi {
    @PostMapping(value = "/pay/add")
    ResultData<Integer> addPay(@RequestBody PayReqVO payReqVO);

    @DeleteMapping(value = "/pay/del/{id}")
    ResultData<Integer> deletePay(@PathVariable("id") Integer id);

    @PutMapping(value = "/pay/update")
    ResultData<Integer> updatePay(@RequestBody PayReqVO payReqVO);

    @GetMapping(value = "/pay/get/{id}")
    ResultData<PayRespVO> getById(@PathVariable("id") Integer id);

    @GetMapping("/consul/test/server")
    ResultData<String> testServer();
}
