package org.jalontsui.cloud.service.impl;

import jakarta.annotation.Resource;
import org.jalontsui.cloud.entities.po.PayPO;
import org.jalontsui.cloud.mapper.PayMapper;
import org.jalontsui.cloud.service.PayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Resource
    private PayMapper payMapper;

    @Override
    public int add(PayPO payPO) {
        return payMapper.insertSelective(payPO);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(PayPO payPO) {
        return payMapper.updateByPrimaryKeySelective(payPO);
    }

    @Override
    public PayPO getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PayPO> getAll() {
        return payMapper.selectAll();
    }
}
