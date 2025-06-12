package org.jalontsui.cloud.entities.respVo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayRespVO {
    private Integer id;
    private String payNo;
    private String orderNo;
    private Integer userId;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
}
