package org.jalontsui.sb.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jalontsui.sb.mysql.entity.po.User;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer selectCount();

    Integer insertUser(User user);

    List<User> selectList();

    // @Param多参数时，可以指定参数的名字
    Integer updateUser(@Param("id") int id, @Param("user") User user);

    Integer deleteUser(@Param("id") int id);
}
