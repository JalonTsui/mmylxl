package org.jalontsui.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.cloud.common.entities.po.PayPO;
import org.jalontsui.cloud.common.entities.reqVo.PayReqVO;
import org.jalontsui.cloud.common.entities.resp.ResultData;
import org.jalontsui.cloud.common.entities.respVo.PayRespVO;
import org.jalontsui.cloud.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/add")
    public ResultData<Integer> addPay(@RequestBody PayReqVO payReqVO) {
        log.info("{}", payReqVO);
        PayPO payPO = new PayPO();
        BeanUtils.copyProperties(payReqVO, payPO);
        int i = payService.add(payPO);
        return ResultData.success(i);
    }

    @DeleteMapping(value = "/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping(value = "/update")
    public ResultData<Integer> updatePay(@RequestBody PayReqVO payReqVO) {
        PayPO payPO = new PayPO();
        BeanUtils.copyProperties(payReqVO, payPO);
        int i = payService.update(payPO);
        return ResultData.success(i);
    }

    @GetMapping(value = "/get/{id}")
    public ResultData<PayRespVO> getById(@PathVariable("id") Integer id) {
        PayRespVO payRespVO = payService.getById(id);
        return ResultData.success(payRespVO);
    }

    @GetMapping("/error/{id}")
    public ResultData<Integer> getPayError(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("id不能为负数");
        }
        return ResultData.success(id);
    }
}
