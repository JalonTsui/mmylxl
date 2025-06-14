package org.jalontsui.cloud.common.entities.reqVo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayReqVO {
    private Integer id;
    private String payNo;
    private String orderNo;
    private Integer userId;
    private BigDecimal amount;
}
