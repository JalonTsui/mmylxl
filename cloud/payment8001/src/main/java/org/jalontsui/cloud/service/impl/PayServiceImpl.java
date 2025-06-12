package org.jalontsui.cloud.service.impl;

import jakarta.annotation.Resource;
import org.jalontsui.cloud.common.entities.po.PayPO;
import org.jalontsui.cloud.common.entities.respVo.PayRespVO;
import org.jalontsui.cloud.mapper.PayMapper;
import org.jalontsui.cloud.service.PayService;
import org.springframework.beans.BeanUtils;
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
    public PayRespVO getById(Integer id) {
        PayPO payPO = payMapper.selectByPrimaryKey(id);
        if (payPO == null) {
            return null;
        }
        PayRespVO payRespVO = new PayRespVO();
        BeanUtils.copyProperties(payPO, payRespVO);
        return payRespVO;
    }

    @Override
    public List<PayPO> getAll() {
        return payMapper.selectAll();
    }
}
