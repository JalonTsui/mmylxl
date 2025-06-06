package org.jalontsui.sb.mysql.controller;

import jakarta.annotation.Resource;
import org.jalontsui.sb.mysql.entity.po.User;
import org.jalontsui.sb.mysql.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/selectAll")
    public List<User> selectUserList(){
        return userService.selectUser();
    }
}
