package org.jalontsui.cloud.common.apis;

import cn.hutool.core.util.IdUtil;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.jalontsui.cloud.common.entities.respVo.PayRespVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 系统内部调用无需走网关，但如果是暴露给外部模块的接口，就走网关，然后再调用对应的微服务
 * 需要在网关中配置好路由
 */

@FeignClient(value = "cloud-gateway")
public interface PayFeignWithGatewayApi {
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData<PayRespVO> getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();
}
