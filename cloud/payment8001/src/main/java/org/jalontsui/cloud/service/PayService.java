package org.jalontsui.cloud.service;

import org.jalontsui.cloud.entities.po.PayPO;

import java.util.List;

public interface PayService {
    int add(PayPO payPO);

    int delete(Integer id);

    int update(PayPO payPO);

    PayPO getById(Integer id);

    List<PayPO> getAll();
}
