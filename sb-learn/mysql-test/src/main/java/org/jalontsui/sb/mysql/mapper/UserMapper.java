package org.jalontsui.sb.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.jalontsui.sb.mysql.entity.po.User;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer selectCount();

    Integer insertUser(User user);

    List<User> selectList();
}
