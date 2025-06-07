package org.jalontsui.sb.mysql;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.sb.mysql.entity.po.Department;
import org.jalontsui.sb.mysql.mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TestDepartmentMapper {
    @Resource
    private DepartmentMapper departmentMapper;

    @Test
    public void testSelectAll() {
        List<Department> departments = departmentMapper.selectAll();
        log.info("{}", departments);
    }
}
