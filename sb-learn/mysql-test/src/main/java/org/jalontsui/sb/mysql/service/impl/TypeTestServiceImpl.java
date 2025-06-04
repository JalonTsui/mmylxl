package org.jalontsui.sb.mysql.service.impl;

import jakarta.annotation.Resource;
import org.jalontsui.sb.mysql.mapper.TypeTestMapper;
import org.jalontsui.sb.mysql.service.TypeTestService;
import org.springframework.stereotype.Service;

@Service
public class TypeTestServiceImpl implements TypeTestService {

    @Resource
    private TypeTestMapper typeTestMapper;

    @Override
    public int selectCount() {
        return typeTestMapper.selectCount();
    }
}
