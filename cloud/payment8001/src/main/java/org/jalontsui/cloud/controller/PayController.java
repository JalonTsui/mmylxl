package org.jalontsui.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.cloud.entities.po.PayPO;
import org.jalontsui.cloud.entities.reqVo.PayReqVO;
import org.jalontsui.cloud.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    public String addPay(@RequestBody PayReqVO payReqVO) {
        log.info("{}", payReqVO);
        PayPO payPO = new PayPO();
        BeanUtils.copyProperties(payReqVO, payPO);
        int i = payService.add(payPO);
        return "成功插入记录，返回值：" + i;
    }

    @DeleteMapping(value = "/pay/del/{id}")
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping(value = "/pay/update")
    public String updatePay(@RequestBody PayReqVO payReqVO) {
        PayPO payPO = new PayPO();
        BeanUtils.copyProperties(payReqVO, payPO);

        int i = payService.update(payPO);
        return "成功修改记录，返回值：" + i;
    }

    @GetMapping(value = "/pay/get/{id}")
    public PayPO getById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }
}
