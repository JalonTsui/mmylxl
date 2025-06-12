package org.jalontsui.cloud.service;


import org.jalontsui.cloud.common.entities.po.PayPO;
import org.jalontsui.cloud.common.entities.respVo.PayRespVO;

import java.util.List;

public interface PayService {
    int add(PayPO payPO);

    int delete(Integer id);

    int update(PayPO payPO);

    PayRespVO getById(Integer id);

    List<PayPO> getAll();
}
