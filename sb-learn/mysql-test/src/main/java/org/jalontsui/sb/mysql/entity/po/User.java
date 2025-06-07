package org.jalontsui.sb.mysql.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private String user;
    private String role;

    // LocalDateTime
    // Date
    // @JsonFormat 只有在返回JSON格式序列化的时候生效
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    private Integer sex;
    private Integer age;
    private Integer id;
    private Integer depId;
}
