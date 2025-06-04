package org.jalontsui.sb.mysql;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.sb.mysql.service.TypeTestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestTypeTestService {

    @Resource
    private TypeTestService typeTestService;

    @Test
    public void testSelectCount() {
        int count = typeTestService.selectCount();
        log.info(Integer.toString(count));
    }
}
