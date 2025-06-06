package org.jalontsui.sb.mysql.service;

import org.jalontsui.sb.mysql.entity.po.User;

import java.util.List;

public interface UserService {
    int selectCount();

    int insertUser(User user);

    List<User> selectUser();

    int updateUser(int id, User user);

    int deleteUser(int id);

    int insertAndReturnKey(User user);
}
