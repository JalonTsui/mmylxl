package org.jalontsui.sb.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeTestMapper {
    Integer selectCount();
}
