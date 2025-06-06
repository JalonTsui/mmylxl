package org.jalontsui.sb.mysql.service.impl;

import jakarta.annotation.Resource;
import org.jalontsui.sb.mysql.entity.po.User;
import org.jalontsui.sb.mysql.mapper.UserMapper;
import org.jalontsui.sb.mysql.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int selectCount() {
        return userMapper.selectCount();
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> selectUser() {
        return userMapper.selectList();
    }


}
