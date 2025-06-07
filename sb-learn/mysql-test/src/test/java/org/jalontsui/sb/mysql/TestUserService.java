package org.jalontsui.sb.mysql;

import jakarta.annotation.Resource;
import org.jalontsui.sb.mysql.entity.po.User;
import org.jalontsui.sb.mysql.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
// @Slf4j
public class TestUserService {
    Logger log = LoggerFactory.getLogger(TestUserService.class);

    @Resource
    private UserService userService;

    @Test
    public void testSelectCount() {
        int count = userService.selectCount();
        log.info(Integer.toString(count));
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUser("jack");
        user.setAge(33);
        user.setSex(1);
        user.setRole("user");
        LocalDateTime date = LocalDateTime.now();
        log.info("{}", date);
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userService.insertUser(user);
    }

    @Test
    public void testSelectUser() {
        List<User> users = userService.selectUser();
        log.info("{}", users);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUser("hello");
        userService.updateUser(1, user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(2);
    }
}
