package org.jalontsui.sb.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.jalontsui.sb.mysql.entity.po.Department;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<Department> selectAll();
}
