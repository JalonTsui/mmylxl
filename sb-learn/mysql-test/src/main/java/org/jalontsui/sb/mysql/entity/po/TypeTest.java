package org.jalontsui.sb.mysql.entity.po;

import lombok.Data;

import java.sql.Date;

@Data
public class TypeTest {
    private String user;
    private String role;
    private Date createTime;
    private Date updateTime;
    private Integer sex;
}
