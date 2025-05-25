package org.jalontsui.login.mock;

import lombok.Data;

/**
 * 模拟数据库查询的用户信息
 */

@Data
public class UserInfo {
    private String account = "testAccount";
    private String pwd = "pwd";
}
